import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDetallePrecuenta, NewDetallePrecuenta } from '../detalle-precuenta.model';

export type PartialUpdateDetallePrecuenta = Partial<IDetallePrecuenta> & Pick<IDetallePrecuenta, 'id'>;

type RestOf<T extends IDetallePrecuenta | NewDetallePrecuenta> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestDetallePrecuenta = RestOf<IDetallePrecuenta>;

export type NewRestDetallePrecuenta = RestOf<NewDetallePrecuenta>;

export type PartialUpdateRestDetallePrecuenta = RestOf<PartialUpdateDetallePrecuenta>;

export type EntityResponseType = HttpResponse<IDetallePrecuenta>;
export type EntityArrayResponseType = HttpResponse<IDetallePrecuenta[]>;

@Injectable({ providedIn: 'root' })
export class DetallePrecuentaService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/detalle-precuentas');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(detallePrecuenta: NewDetallePrecuenta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(detallePrecuenta);
    return this.http
      .post<RestDetallePrecuenta>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(detallePrecuenta: IDetallePrecuenta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(detallePrecuenta);
    return this.http
      .put<RestDetallePrecuenta>(`${this.resourceUrl}/${this.getDetallePrecuentaIdentifier(detallePrecuenta)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(detallePrecuenta: PartialUpdateDetallePrecuenta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(detallePrecuenta);
    return this.http
      .patch<RestDetallePrecuenta>(`${this.resourceUrl}/${this.getDetallePrecuentaIdentifier(detallePrecuenta)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestDetallePrecuenta>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestDetallePrecuenta[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getDetallePrecuentaIdentifier(detallePrecuenta: Pick<IDetallePrecuenta, 'id'>): string {
    return detallePrecuenta.id;
  }

  compareDetallePrecuenta(o1: Pick<IDetallePrecuenta, 'id'> | null, o2: Pick<IDetallePrecuenta, 'id'> | null): boolean {
    return o1 && o2 ? this.getDetallePrecuentaIdentifier(o1) === this.getDetallePrecuentaIdentifier(o2) : o1 === o2;
  }

  addDetallePrecuentaToCollectionIfMissing<Type extends Pick<IDetallePrecuenta, 'id'>>(
    detallePrecuentaCollection: Type[],
    ...detallePrecuentasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const detallePrecuentas: Type[] = detallePrecuentasToCheck.filter(isPresent);
    if (detallePrecuentas.length > 0) {
      const detallePrecuentaCollectionIdentifiers = detallePrecuentaCollection.map(
        detallePrecuentaItem => this.getDetallePrecuentaIdentifier(detallePrecuentaItem)!
      );
      const detallePrecuentasToAdd = detallePrecuentas.filter(detallePrecuentaItem => {
        const detallePrecuentaIdentifier = this.getDetallePrecuentaIdentifier(detallePrecuentaItem);
        if (detallePrecuentaCollectionIdentifiers.includes(detallePrecuentaIdentifier)) {
          return false;
        }
        detallePrecuentaCollectionIdentifiers.push(detallePrecuentaIdentifier);
        return true;
      });
      return [...detallePrecuentasToAdd, ...detallePrecuentaCollection];
    }
    return detallePrecuentaCollection;
  }

  protected convertDateFromClient<T extends IDetallePrecuenta | NewDetallePrecuenta | PartialUpdateDetallePrecuenta>(
    detallePrecuenta: T
  ): RestOf<T> {
    return {
      ...detallePrecuenta,
      fecCrea: detallePrecuenta.fecCrea?.toJSON() ?? null,
      fecModif: detallePrecuenta.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restDetallePrecuenta: RestDetallePrecuenta): IDetallePrecuenta {
    return {
      ...restDetallePrecuenta,
      fecCrea: restDetallePrecuenta.fecCrea ? dayjs(restDetallePrecuenta.fecCrea) : undefined,
      fecModif: restDetallePrecuenta.fecModif ? dayjs(restDetallePrecuenta.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestDetallePrecuenta>): HttpResponse<IDetallePrecuenta> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestDetallePrecuenta[]>): HttpResponse<IDetallePrecuenta[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
