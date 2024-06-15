import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IRegCompras, NewRegCompras } from '../reg-compras.model';

export type PartialUpdateRegCompras = Partial<IRegCompras> & Pick<IRegCompras, 'id'>;

type RestOf<T extends IRegCompras | NewRegCompras> = Omit<
  T,
  'fecEmiComp' | 'fecVencComp' | 'fecEmiModif' | 'fecEmiDetracc' | 'fecCrea' | 'fecModif'
> & {
  fecEmiComp?: string | null;
  fecVencComp?: string | null;
  fecEmiModif?: string | null;
  fecEmiDetracc?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestRegCompras = RestOf<IRegCompras>;

export type NewRestRegCompras = RestOf<NewRegCompras>;

export type PartialUpdateRestRegCompras = RestOf<PartialUpdateRegCompras>;

export type EntityResponseType = HttpResponse<IRegCompras>;
export type EntityArrayResponseType = HttpResponse<IRegCompras[]>;

@Injectable({ providedIn: 'root' })
export class RegComprasService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/reg-compras');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(regCompras: NewRegCompras): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(regCompras);
    return this.http
      .post<RestRegCompras>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(regCompras: IRegCompras): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(regCompras);
    return this.http
      .put<RestRegCompras>(`${this.resourceUrl}/${this.getRegComprasIdentifier(regCompras)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(regCompras: PartialUpdateRegCompras): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(regCompras);
    return this.http
      .patch<RestRegCompras>(`${this.resourceUrl}/${this.getRegComprasIdentifier(regCompras)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestRegCompras>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestRegCompras[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getRegComprasIdentifier(regCompras: Pick<IRegCompras, 'id'>): string {
    return regCompras.id;
  }

  compareRegCompras(o1: Pick<IRegCompras, 'id'> | null, o2: Pick<IRegCompras, 'id'> | null): boolean {
    return o1 && o2 ? this.getRegComprasIdentifier(o1) === this.getRegComprasIdentifier(o2) : o1 === o2;
  }

  addRegComprasToCollectionIfMissing<Type extends Pick<IRegCompras, 'id'>>(
    regComprasCollection: Type[],
    ...regComprasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const regCompras: Type[] = regComprasToCheck.filter(isPresent);
    if (regCompras.length > 0) {
      const regComprasCollectionIdentifiers = regComprasCollection.map(regComprasItem => this.getRegComprasIdentifier(regComprasItem)!);
      const regComprasToAdd = regCompras.filter(regComprasItem => {
        const regComprasIdentifier = this.getRegComprasIdentifier(regComprasItem);
        if (regComprasCollectionIdentifiers.includes(regComprasIdentifier)) {
          return false;
        }
        regComprasCollectionIdentifiers.push(regComprasIdentifier);
        return true;
      });
      return [...regComprasToAdd, ...regComprasCollection];
    }
    return regComprasCollection;
  }

  protected convertDateFromClient<T extends IRegCompras | NewRegCompras | PartialUpdateRegCompras>(regCompras: T): RestOf<T> {
    return {
      ...regCompras,
      fecEmiComp: regCompras.fecEmiComp?.toJSON() ?? null,
      fecVencComp: regCompras.fecVencComp?.toJSON() ?? null,
      fecEmiModif: regCompras.fecEmiModif?.toJSON() ?? null,
      fecEmiDetracc: regCompras.fecEmiDetracc?.toJSON() ?? null,
      fecCrea: regCompras.fecCrea?.toJSON() ?? null,
      fecModif: regCompras.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restRegCompras: RestRegCompras): IRegCompras {
    return {
      ...restRegCompras,
      fecEmiComp: restRegCompras.fecEmiComp ? dayjs(restRegCompras.fecEmiComp) : undefined,
      fecVencComp: restRegCompras.fecVencComp ? dayjs(restRegCompras.fecVencComp) : undefined,
      fecEmiModif: restRegCompras.fecEmiModif ? dayjs(restRegCompras.fecEmiModif) : undefined,
      fecEmiDetracc: restRegCompras.fecEmiDetracc ? dayjs(restRegCompras.fecEmiDetracc) : undefined,
      fecCrea: restRegCompras.fecCrea ? dayjs(restRegCompras.fecCrea) : undefined,
      fecModif: restRegCompras.fecModif ? dayjs(restRegCompras.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestRegCompras>): HttpResponse<IRegCompras> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestRegCompras[]>): HttpResponse<IRegCompras[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
