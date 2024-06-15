import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IRegVenta, NewRegVenta } from '../reg-venta.model';

export type PartialUpdateRegVenta = Partial<IRegVenta> & Pick<IRegVenta, 'id'>;

type RestOf<T extends IRegVenta | NewRegVenta> = Omit<T, 'fecEmiComp' | 'fecVencComp' | 'fecEmiModif' | 'fecCrea' | 'fecModif'> & {
  fecEmiComp?: string | null;
  fecVencComp?: string | null;
  fecEmiModif?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestRegVenta = RestOf<IRegVenta>;

export type NewRestRegVenta = RestOf<NewRegVenta>;

export type PartialUpdateRestRegVenta = RestOf<PartialUpdateRegVenta>;

export type EntityResponseType = HttpResponse<IRegVenta>;
export type EntityArrayResponseType = HttpResponse<IRegVenta[]>;

@Injectable({ providedIn: 'root' })
export class RegVentaService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/reg-ventas');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(regVenta: NewRegVenta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(regVenta);
    return this.http
      .post<RestRegVenta>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(regVenta: IRegVenta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(regVenta);
    return this.http
      .put<RestRegVenta>(`${this.resourceUrl}/${this.getRegVentaIdentifier(regVenta)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(regVenta: PartialUpdateRegVenta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(regVenta);
    return this.http
      .patch<RestRegVenta>(`${this.resourceUrl}/${this.getRegVentaIdentifier(regVenta)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestRegVenta>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestRegVenta[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getRegVentaIdentifier(regVenta: Pick<IRegVenta, 'id'>): string {
    return regVenta.id;
  }

  compareRegVenta(o1: Pick<IRegVenta, 'id'> | null, o2: Pick<IRegVenta, 'id'> | null): boolean {
    return o1 && o2 ? this.getRegVentaIdentifier(o1) === this.getRegVentaIdentifier(o2) : o1 === o2;
  }

  addRegVentaToCollectionIfMissing<Type extends Pick<IRegVenta, 'id'>>(
    regVentaCollection: Type[],
    ...regVentasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const regVentas: Type[] = regVentasToCheck.filter(isPresent);
    if (regVentas.length > 0) {
      const regVentaCollectionIdentifiers = regVentaCollection.map(regVentaItem => this.getRegVentaIdentifier(regVentaItem)!);
      const regVentasToAdd = regVentas.filter(regVentaItem => {
        const regVentaIdentifier = this.getRegVentaIdentifier(regVentaItem);
        if (regVentaCollectionIdentifiers.includes(regVentaIdentifier)) {
          return false;
        }
        regVentaCollectionIdentifiers.push(regVentaIdentifier);
        return true;
      });
      return [...regVentasToAdd, ...regVentaCollection];
    }
    return regVentaCollection;
  }

  protected convertDateFromClient<T extends IRegVenta | NewRegVenta | PartialUpdateRegVenta>(regVenta: T): RestOf<T> {
    return {
      ...regVenta,
      fecEmiComp: regVenta.fecEmiComp?.toJSON() ?? null,
      fecVencComp: regVenta.fecVencComp?.toJSON() ?? null,
      fecEmiModif: regVenta.fecEmiModif?.toJSON() ?? null,
      fecCrea: regVenta.fecCrea?.toJSON() ?? null,
      fecModif: regVenta.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restRegVenta: RestRegVenta): IRegVenta {
    return {
      ...restRegVenta,
      fecEmiComp: restRegVenta.fecEmiComp ? dayjs(restRegVenta.fecEmiComp) : undefined,
      fecVencComp: restRegVenta.fecVencComp ? dayjs(restRegVenta.fecVencComp) : undefined,
      fecEmiModif: restRegVenta.fecEmiModif ? dayjs(restRegVenta.fecEmiModif) : undefined,
      fecCrea: restRegVenta.fecCrea ? dayjs(restRegVenta.fecCrea) : undefined,
      fecModif: restRegVenta.fecModif ? dayjs(restRegVenta.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestRegVenta>): HttpResponse<IRegVenta> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestRegVenta[]>): HttpResponse<IRegVenta[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
