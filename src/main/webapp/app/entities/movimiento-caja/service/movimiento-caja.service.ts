import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IMovimientoCaja, NewMovimientoCaja } from '../movimiento-caja.model';

export type PartialUpdateMovimientoCaja = Partial<IMovimientoCaja> & Pick<IMovimientoCaja, 'id'>;

type RestOf<T extends IMovimientoCaja | NewMovimientoCaja> = Omit<T, 'fecMovimiento' | 'fecCrea' | 'fecModif'> & {
  fecMovimiento?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestMovimientoCaja = RestOf<IMovimientoCaja>;

export type NewRestMovimientoCaja = RestOf<NewMovimientoCaja>;

export type PartialUpdateRestMovimientoCaja = RestOf<PartialUpdateMovimientoCaja>;

export type EntityResponseType = HttpResponse<IMovimientoCaja>;
export type EntityArrayResponseType = HttpResponse<IMovimientoCaja[]>;

@Injectable({ providedIn: 'root' })
export class MovimientoCajaService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/movimiento-cajas');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(movimientoCaja: NewMovimientoCaja): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(movimientoCaja);
    return this.http
      .post<RestMovimientoCaja>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(movimientoCaja: IMovimientoCaja): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(movimientoCaja);
    return this.http
      .put<RestMovimientoCaja>(`${this.resourceUrl}/${this.getMovimientoCajaIdentifier(movimientoCaja)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(movimientoCaja: PartialUpdateMovimientoCaja): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(movimientoCaja);
    return this.http
      .patch<RestMovimientoCaja>(`${this.resourceUrl}/${this.getMovimientoCajaIdentifier(movimientoCaja)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestMovimientoCaja>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestMovimientoCaja[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getMovimientoCajaIdentifier(movimientoCaja: Pick<IMovimientoCaja, 'id'>): string {
    return movimientoCaja.id;
  }

  compareMovimientoCaja(o1: Pick<IMovimientoCaja, 'id'> | null, o2: Pick<IMovimientoCaja, 'id'> | null): boolean {
    return o1 && o2 ? this.getMovimientoCajaIdentifier(o1) === this.getMovimientoCajaIdentifier(o2) : o1 === o2;
  }

  addMovimientoCajaToCollectionIfMissing<Type extends Pick<IMovimientoCaja, 'id'>>(
    movimientoCajaCollection: Type[],
    ...movimientoCajasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const movimientoCajas: Type[] = movimientoCajasToCheck.filter(isPresent);
    if (movimientoCajas.length > 0) {
      const movimientoCajaCollectionIdentifiers = movimientoCajaCollection.map(
        movimientoCajaItem => this.getMovimientoCajaIdentifier(movimientoCajaItem)!
      );
      const movimientoCajasToAdd = movimientoCajas.filter(movimientoCajaItem => {
        const movimientoCajaIdentifier = this.getMovimientoCajaIdentifier(movimientoCajaItem);
        if (movimientoCajaCollectionIdentifiers.includes(movimientoCajaIdentifier)) {
          return false;
        }
        movimientoCajaCollectionIdentifiers.push(movimientoCajaIdentifier);
        return true;
      });
      return [...movimientoCajasToAdd, ...movimientoCajaCollection];
    }
    return movimientoCajaCollection;
  }

  protected convertDateFromClient<T extends IMovimientoCaja | NewMovimientoCaja | PartialUpdateMovimientoCaja>(
    movimientoCaja: T
  ): RestOf<T> {
    return {
      ...movimientoCaja,
      fecMovimiento: movimientoCaja.fecMovimiento?.toJSON() ?? null,
      fecCrea: movimientoCaja.fecCrea?.toJSON() ?? null,
      fecModif: movimientoCaja.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restMovimientoCaja: RestMovimientoCaja): IMovimientoCaja {
    return {
      ...restMovimientoCaja,
      fecMovimiento: restMovimientoCaja.fecMovimiento ? dayjs(restMovimientoCaja.fecMovimiento) : undefined,
      fecCrea: restMovimientoCaja.fecCrea ? dayjs(restMovimientoCaja.fecCrea) : undefined,
      fecModif: restMovimientoCaja.fecModif ? dayjs(restMovimientoCaja.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestMovimientoCaja>): HttpResponse<IMovimientoCaja> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestMovimientoCaja[]>): HttpResponse<IMovimientoCaja[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
