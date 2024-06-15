import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDetalleOrden, NewDetalleOrden } from '../detalle-orden.model';

export type PartialUpdateDetalleOrden = Partial<IDetalleOrden> & Pick<IDetalleOrden, 'id'>;

type RestOf<T extends IDetalleOrden | NewDetalleOrden> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestDetalleOrden = RestOf<IDetalleOrden>;

export type NewRestDetalleOrden = RestOf<NewDetalleOrden>;

export type PartialUpdateRestDetalleOrden = RestOf<PartialUpdateDetalleOrden>;

export type EntityResponseType = HttpResponse<IDetalleOrden>;
export type EntityArrayResponseType = HttpResponse<IDetalleOrden[]>;

@Injectable({ providedIn: 'root' })
export class DetalleOrdenService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/detalle-ordens');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(detalleOrden: NewDetalleOrden): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(detalleOrden);
    return this.http
      .post<RestDetalleOrden>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(detalleOrden: IDetalleOrden): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(detalleOrden);
    return this.http
      .put<RestDetalleOrden>(`${this.resourceUrl}/${this.getDetalleOrdenIdentifier(detalleOrden)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(detalleOrden: PartialUpdateDetalleOrden): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(detalleOrden);
    return this.http
      .patch<RestDetalleOrden>(`${this.resourceUrl}/${this.getDetalleOrdenIdentifier(detalleOrden)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestDetalleOrden>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestDetalleOrden[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getDetalleOrdenIdentifier(detalleOrden: Pick<IDetalleOrden, 'id'>): string {
    return detalleOrden.id;
  }

  compareDetalleOrden(o1: Pick<IDetalleOrden, 'id'> | null, o2: Pick<IDetalleOrden, 'id'> | null): boolean {
    return o1 && o2 ? this.getDetalleOrdenIdentifier(o1) === this.getDetalleOrdenIdentifier(o2) : o1 === o2;
  }

  addDetalleOrdenToCollectionIfMissing<Type extends Pick<IDetalleOrden, 'id'>>(
    detalleOrdenCollection: Type[],
    ...detalleOrdensToCheck: (Type | null | undefined)[]
  ): Type[] {
    const detalleOrdens: Type[] = detalleOrdensToCheck.filter(isPresent);
    if (detalleOrdens.length > 0) {
      const detalleOrdenCollectionIdentifiers = detalleOrdenCollection.map(
        detalleOrdenItem => this.getDetalleOrdenIdentifier(detalleOrdenItem)!
      );
      const detalleOrdensToAdd = detalleOrdens.filter(detalleOrdenItem => {
        const detalleOrdenIdentifier = this.getDetalleOrdenIdentifier(detalleOrdenItem);
        if (detalleOrdenCollectionIdentifiers.includes(detalleOrdenIdentifier)) {
          return false;
        }
        detalleOrdenCollectionIdentifiers.push(detalleOrdenIdentifier);
        return true;
      });
      return [...detalleOrdensToAdd, ...detalleOrdenCollection];
    }
    return detalleOrdenCollection;
  }

  protected convertDateFromClient<T extends IDetalleOrden | NewDetalleOrden | PartialUpdateDetalleOrden>(detalleOrden: T): RestOf<T> {
    return {
      ...detalleOrden,
      fecCrea: detalleOrden.fecCrea?.toJSON() ?? null,
      fecModif: detalleOrden.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restDetalleOrden: RestDetalleOrden): IDetalleOrden {
    return {
      ...restDetalleOrden,
      fecCrea: restDetalleOrden.fecCrea ? dayjs(restDetalleOrden.fecCrea) : undefined,
      fecModif: restDetalleOrden.fecModif ? dayjs(restDetalleOrden.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestDetalleOrden>): HttpResponse<IDetalleOrden> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestDetalleOrden[]>): HttpResponse<IDetalleOrden[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
