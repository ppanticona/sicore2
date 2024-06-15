import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICaja, NewCaja } from '../caja.model';

export type PartialUpdateCaja = Partial<ICaja> & Pick<ICaja, 'id'>;

type RestOf<T extends ICaja | NewCaja> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestCaja = RestOf<ICaja>;

export type NewRestCaja = RestOf<NewCaja>;

export type PartialUpdateRestCaja = RestOf<PartialUpdateCaja>;

export type EntityResponseType = HttpResponse<ICaja>;
export type EntityArrayResponseType = HttpResponse<ICaja[]>;

@Injectable({ providedIn: 'root' })
export class CajaService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/cajas');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(caja: NewCaja): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(caja);
    return this.http.post<RestCaja>(this.resourceUrl, copy, { observe: 'response' }).pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(caja: ICaja): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(caja);
    return this.http
      .put<RestCaja>(`${this.resourceUrl}/${this.getCajaIdentifier(caja)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(caja: PartialUpdateCaja): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(caja);
    return this.http
      .patch<RestCaja>(`${this.resourceUrl}/${this.getCajaIdentifier(caja)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestCaja>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestCaja[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCajaIdentifier(caja: Pick<ICaja, 'id'>): string {
    return caja.id;
  }

  compareCaja(o1: Pick<ICaja, 'id'> | null, o2: Pick<ICaja, 'id'> | null): boolean {
    return o1 && o2 ? this.getCajaIdentifier(o1) === this.getCajaIdentifier(o2) : o1 === o2;
  }

  addCajaToCollectionIfMissing<Type extends Pick<ICaja, 'id'>>(
    cajaCollection: Type[],
    ...cajasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const cajas: Type[] = cajasToCheck.filter(isPresent);
    if (cajas.length > 0) {
      const cajaCollectionIdentifiers = cajaCollection.map(cajaItem => this.getCajaIdentifier(cajaItem)!);
      const cajasToAdd = cajas.filter(cajaItem => {
        const cajaIdentifier = this.getCajaIdentifier(cajaItem);
        if (cajaCollectionIdentifiers.includes(cajaIdentifier)) {
          return false;
        }
        cajaCollectionIdentifiers.push(cajaIdentifier);
        return true;
      });
      return [...cajasToAdd, ...cajaCollection];
    }
    return cajaCollection;
  }

  protected convertDateFromClient<T extends ICaja | NewCaja | PartialUpdateCaja>(caja: T): RestOf<T> {
    return {
      ...caja,
      fecCrea: caja.fecCrea?.toJSON() ?? null,
      fecModif: caja.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restCaja: RestCaja): ICaja {
    return {
      ...restCaja,
      fecCrea: restCaja.fecCrea ? dayjs(restCaja.fecCrea) : undefined,
      fecModif: restCaja.fecModif ? dayjs(restCaja.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestCaja>): HttpResponse<ICaja> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestCaja[]>): HttpResponse<ICaja[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
