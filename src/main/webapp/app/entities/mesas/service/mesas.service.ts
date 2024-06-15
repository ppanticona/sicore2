import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IMesas, NewMesas } from '../mesas.model';

export type PartialUpdateMesas = Partial<IMesas> & Pick<IMesas, 'id'>;

type RestOf<T extends IMesas | NewMesas> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestMesas = RestOf<IMesas>;

export type NewRestMesas = RestOf<NewMesas>;

export type PartialUpdateRestMesas = RestOf<PartialUpdateMesas>;

export type EntityResponseType = HttpResponse<IMesas>;
export type EntityArrayResponseType = HttpResponse<IMesas[]>;

@Injectable({ providedIn: 'root' })
export class MesasService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/mesas');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(mesas: NewMesas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(mesas);
    return this.http.post<RestMesas>(this.resourceUrl, copy, { observe: 'response' }).pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(mesas: IMesas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(mesas);
    return this.http
      .put<RestMesas>(`${this.resourceUrl}/${this.getMesasIdentifier(mesas)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(mesas: PartialUpdateMesas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(mesas);
    return this.http
      .patch<RestMesas>(`${this.resourceUrl}/${this.getMesasIdentifier(mesas)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestMesas>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestMesas[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getMesasIdentifier(mesas: Pick<IMesas, 'id'>): string {
    return mesas.id;
  }

  compareMesas(o1: Pick<IMesas, 'id'> | null, o2: Pick<IMesas, 'id'> | null): boolean {
    return o1 && o2 ? this.getMesasIdentifier(o1) === this.getMesasIdentifier(o2) : o1 === o2;
  }

  addMesasToCollectionIfMissing<Type extends Pick<IMesas, 'id'>>(
    mesasCollection: Type[],
    ...mesasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const mesas: Type[] = mesasToCheck.filter(isPresent);
    if (mesas.length > 0) {
      const mesasCollectionIdentifiers = mesasCollection.map(mesasItem => this.getMesasIdentifier(mesasItem)!);
      const mesasToAdd = mesas.filter(mesasItem => {
        const mesasIdentifier = this.getMesasIdentifier(mesasItem);
        if (mesasCollectionIdentifiers.includes(mesasIdentifier)) {
          return false;
        }
        mesasCollectionIdentifiers.push(mesasIdentifier);
        return true;
      });
      return [...mesasToAdd, ...mesasCollection];
    }
    return mesasCollection;
  }

  protected convertDateFromClient<T extends IMesas | NewMesas | PartialUpdateMesas>(mesas: T): RestOf<T> {
    return {
      ...mesas,
      fecCrea: mesas.fecCrea?.toJSON() ?? null,
      fecModif: mesas.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restMesas: RestMesas): IMesas {
    return {
      ...restMesas,
      fecCrea: restMesas.fecCrea ? dayjs(restMesas.fecCrea) : undefined,
      fecModif: restMesas.fecModif ? dayjs(restMesas.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestMesas>): HttpResponse<IMesas> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestMesas[]>): HttpResponse<IMesas[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
