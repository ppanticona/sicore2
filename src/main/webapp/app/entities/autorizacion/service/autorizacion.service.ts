import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAutorizacion, NewAutorizacion } from '../autorizacion.model';

export type PartialUpdateAutorizacion = Partial<IAutorizacion> & Pick<IAutorizacion, 'id'>;

type RestOf<T extends IAutorizacion | NewAutorizacion> = Omit<T, 'fecIniVig' | 'fecFinVig' | 'fecCrea' | 'fecModif'> & {
  fecIniVig?: string | null;
  fecFinVig?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestAutorizacion = RestOf<IAutorizacion>;

export type NewRestAutorizacion = RestOf<NewAutorizacion>;

export type PartialUpdateRestAutorizacion = RestOf<PartialUpdateAutorizacion>;

export type EntityResponseType = HttpResponse<IAutorizacion>;
export type EntityArrayResponseType = HttpResponse<IAutorizacion[]>;

@Injectable({ providedIn: 'root' })
export class AutorizacionService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/autorizacions');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(autorizacion: NewAutorizacion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autorizacion);
    return this.http
      .post<RestAutorizacion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(autorizacion: IAutorizacion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autorizacion);
    return this.http
      .put<RestAutorizacion>(`${this.resourceUrl}/${this.getAutorizacionIdentifier(autorizacion)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(autorizacion: PartialUpdateAutorizacion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autorizacion);
    return this.http
      .patch<RestAutorizacion>(`${this.resourceUrl}/${this.getAutorizacionIdentifier(autorizacion)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestAutorizacion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAutorizacion[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAutorizacionIdentifier(autorizacion: Pick<IAutorizacion, 'id'>): string {
    return autorizacion.id;
  }

  compareAutorizacion(o1: Pick<IAutorizacion, 'id'> | null, o2: Pick<IAutorizacion, 'id'> | null): boolean {
    return o1 && o2 ? this.getAutorizacionIdentifier(o1) === this.getAutorizacionIdentifier(o2) : o1 === o2;
  }

  addAutorizacionToCollectionIfMissing<Type extends Pick<IAutorizacion, 'id'>>(
    autorizacionCollection: Type[],
    ...autorizacionsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const autorizacions: Type[] = autorizacionsToCheck.filter(isPresent);
    if (autorizacions.length > 0) {
      const autorizacionCollectionIdentifiers = autorizacionCollection.map(
        autorizacionItem => this.getAutorizacionIdentifier(autorizacionItem)!
      );
      const autorizacionsToAdd = autorizacions.filter(autorizacionItem => {
        const autorizacionIdentifier = this.getAutorizacionIdentifier(autorizacionItem);
        if (autorizacionCollectionIdentifiers.includes(autorizacionIdentifier)) {
          return false;
        }
        autorizacionCollectionIdentifiers.push(autorizacionIdentifier);
        return true;
      });
      return [...autorizacionsToAdd, ...autorizacionCollection];
    }
    return autorizacionCollection;
  }

  protected convertDateFromClient<T extends IAutorizacion | NewAutorizacion | PartialUpdateAutorizacion>(autorizacion: T): RestOf<T> {
    return {
      ...autorizacion,
      fecIniVig: autorizacion.fecIniVig?.toJSON() ?? null,
      fecFinVig: autorizacion.fecFinVig?.toJSON() ?? null,
      fecCrea: autorizacion.fecCrea?.toJSON() ?? null,
      fecModif: autorizacion.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restAutorizacion: RestAutorizacion): IAutorizacion {
    return {
      ...restAutorizacion,
      fecIniVig: restAutorizacion.fecIniVig ? dayjs(restAutorizacion.fecIniVig) : undefined,
      fecFinVig: restAutorizacion.fecFinVig ? dayjs(restAutorizacion.fecFinVig) : undefined,
      fecCrea: restAutorizacion.fecCrea ? dayjs(restAutorizacion.fecCrea) : undefined,
      fecModif: restAutorizacion.fecModif ? dayjs(restAutorizacion.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAutorizacion>): HttpResponse<IAutorizacion> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAutorizacion[]>): HttpResponse<IAutorizacion[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
