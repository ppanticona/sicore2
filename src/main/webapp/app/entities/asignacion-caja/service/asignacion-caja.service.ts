import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAsignacionCaja, NewAsignacionCaja } from '../asignacion-caja.model';

export type PartialUpdateAsignacionCaja = Partial<IAsignacionCaja> & Pick<IAsignacionCaja, 'id'>;

type RestOf<T extends IAsignacionCaja | NewAsignacionCaja> = Omit<T, 'fecAsignacion' | 'fecCrea' | 'fecModif'> & {
  fecAsignacion?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestAsignacionCaja = RestOf<IAsignacionCaja>;

export type NewRestAsignacionCaja = RestOf<NewAsignacionCaja>;

export type PartialUpdateRestAsignacionCaja = RestOf<PartialUpdateAsignacionCaja>;

export type EntityResponseType = HttpResponse<IAsignacionCaja>;
export type EntityArrayResponseType = HttpResponse<IAsignacionCaja[]>;

@Injectable({ providedIn: 'root' })
export class AsignacionCajaService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/asignacion-cajas');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(asignacionCaja: NewAsignacionCaja): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(asignacionCaja);
    return this.http
      .post<RestAsignacionCaja>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(asignacionCaja: IAsignacionCaja): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(asignacionCaja);
    return this.http
      .put<RestAsignacionCaja>(`${this.resourceUrl}/${this.getAsignacionCajaIdentifier(asignacionCaja)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(asignacionCaja: PartialUpdateAsignacionCaja): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(asignacionCaja);
    return this.http
      .patch<RestAsignacionCaja>(`${this.resourceUrl}/${this.getAsignacionCajaIdentifier(asignacionCaja)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestAsignacionCaja>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAsignacionCaja[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAsignacionCajaIdentifier(asignacionCaja: Pick<IAsignacionCaja, 'id'>): string {
    return asignacionCaja.id;
  }

  compareAsignacionCaja(o1: Pick<IAsignacionCaja, 'id'> | null, o2: Pick<IAsignacionCaja, 'id'> | null): boolean {
    return o1 && o2 ? this.getAsignacionCajaIdentifier(o1) === this.getAsignacionCajaIdentifier(o2) : o1 === o2;
  }

  addAsignacionCajaToCollectionIfMissing<Type extends Pick<IAsignacionCaja, 'id'>>(
    asignacionCajaCollection: Type[],
    ...asignacionCajasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const asignacionCajas: Type[] = asignacionCajasToCheck.filter(isPresent);
    if (asignacionCajas.length > 0) {
      const asignacionCajaCollectionIdentifiers = asignacionCajaCollection.map(
        asignacionCajaItem => this.getAsignacionCajaIdentifier(asignacionCajaItem)!
      );
      const asignacionCajasToAdd = asignacionCajas.filter(asignacionCajaItem => {
        const asignacionCajaIdentifier = this.getAsignacionCajaIdentifier(asignacionCajaItem);
        if (asignacionCajaCollectionIdentifiers.includes(asignacionCajaIdentifier)) {
          return false;
        }
        asignacionCajaCollectionIdentifiers.push(asignacionCajaIdentifier);
        return true;
      });
      return [...asignacionCajasToAdd, ...asignacionCajaCollection];
    }
    return asignacionCajaCollection;
  }

  protected convertDateFromClient<T extends IAsignacionCaja | NewAsignacionCaja | PartialUpdateAsignacionCaja>(
    asignacionCaja: T
  ): RestOf<T> {
    return {
      ...asignacionCaja,
      fecAsignacion: asignacionCaja.fecAsignacion?.toJSON() ?? null,
      fecCrea: asignacionCaja.fecCrea?.toJSON() ?? null,
      fecModif: asignacionCaja.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restAsignacionCaja: RestAsignacionCaja): IAsignacionCaja {
    return {
      ...restAsignacionCaja,
      fecAsignacion: restAsignacionCaja.fecAsignacion ? dayjs(restAsignacionCaja.fecAsignacion) : undefined,
      fecCrea: restAsignacionCaja.fecCrea ? dayjs(restAsignacionCaja.fecCrea) : undefined,
      fecModif: restAsignacionCaja.fecModif ? dayjs(restAsignacionCaja.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAsignacionCaja>): HttpResponse<IAsignacionCaja> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAsignacionCaja[]>): HttpResponse<IAsignacionCaja[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
