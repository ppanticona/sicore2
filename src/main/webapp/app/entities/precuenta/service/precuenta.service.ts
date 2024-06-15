import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IPrecuenta, NewPrecuenta } from '../precuenta.model';

export type PartialUpdatePrecuenta = Partial<IPrecuenta> & Pick<IPrecuenta, 'id'>;

type RestOf<T extends IPrecuenta | NewPrecuenta> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestPrecuenta = RestOf<IPrecuenta>;

export type NewRestPrecuenta = RestOf<NewPrecuenta>;

export type PartialUpdateRestPrecuenta = RestOf<PartialUpdatePrecuenta>;

export type EntityResponseType = HttpResponse<IPrecuenta>;
export type EntityArrayResponseType = HttpResponse<IPrecuenta[]>;

@Injectable({ providedIn: 'root' })
export class PrecuentaService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/precuentas');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(precuenta: NewPrecuenta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(precuenta);
    return this.http
      .post<RestPrecuenta>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(precuenta: IPrecuenta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(precuenta);
    return this.http
      .put<RestPrecuenta>(`${this.resourceUrl}/${this.getPrecuentaIdentifier(precuenta)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(precuenta: PartialUpdatePrecuenta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(precuenta);
    return this.http
      .patch<RestPrecuenta>(`${this.resourceUrl}/${this.getPrecuentaIdentifier(precuenta)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestPrecuenta>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestPrecuenta[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getPrecuentaIdentifier(precuenta: Pick<IPrecuenta, 'id'>): string {
    return precuenta.id;
  }

  comparePrecuenta(o1: Pick<IPrecuenta, 'id'> | null, o2: Pick<IPrecuenta, 'id'> | null): boolean {
    return o1 && o2 ? this.getPrecuentaIdentifier(o1) === this.getPrecuentaIdentifier(o2) : o1 === o2;
  }

  addPrecuentaToCollectionIfMissing<Type extends Pick<IPrecuenta, 'id'>>(
    precuentaCollection: Type[],
    ...precuentasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const precuentas: Type[] = precuentasToCheck.filter(isPresent);
    if (precuentas.length > 0) {
      const precuentaCollectionIdentifiers = precuentaCollection.map(precuentaItem => this.getPrecuentaIdentifier(precuentaItem)!);
      const precuentasToAdd = precuentas.filter(precuentaItem => {
        const precuentaIdentifier = this.getPrecuentaIdentifier(precuentaItem);
        if (precuentaCollectionIdentifiers.includes(precuentaIdentifier)) {
          return false;
        }
        precuentaCollectionIdentifiers.push(precuentaIdentifier);
        return true;
      });
      return [...precuentasToAdd, ...precuentaCollection];
    }
    return precuentaCollection;
  }

  protected convertDateFromClient<T extends IPrecuenta | NewPrecuenta | PartialUpdatePrecuenta>(precuenta: T): RestOf<T> {
    return {
      ...precuenta,
      fecCrea: precuenta.fecCrea?.toJSON() ?? null,
      fecModif: precuenta.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restPrecuenta: RestPrecuenta): IPrecuenta {
    return {
      ...restPrecuenta,
      fecCrea: restPrecuenta.fecCrea ? dayjs(restPrecuenta.fecCrea) : undefined,
      fecModif: restPrecuenta.fecModif ? dayjs(restPrecuenta.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestPrecuenta>): HttpResponse<IPrecuenta> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestPrecuenta[]>): HttpResponse<IPrecuenta[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
