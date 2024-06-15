import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISede, NewSede } from '../sede.model';

export type PartialUpdateSede = Partial<ISede> & Pick<ISede, 'id'>;

type RestOf<T extends ISede | NewSede> = Omit<T, 'fecAper' | 'fecCrea' | 'fecModif'> & {
  fecAper?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestSede = RestOf<ISede>;

export type NewRestSede = RestOf<NewSede>;

export type PartialUpdateRestSede = RestOf<PartialUpdateSede>;

export type EntityResponseType = HttpResponse<ISede>;
export type EntityArrayResponseType = HttpResponse<ISede[]>;

@Injectable({ providedIn: 'root' })
export class SedeService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sedes');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(sede: NewSede): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sede);
    return this.http.post<RestSede>(this.resourceUrl, copy, { observe: 'response' }).pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(sede: ISede): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sede);
    return this.http
      .put<RestSede>(`${this.resourceUrl}/${this.getSedeIdentifier(sede)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(sede: PartialUpdateSede): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sede);
    return this.http
      .patch<RestSede>(`${this.resourceUrl}/${this.getSedeIdentifier(sede)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestSede>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSede[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSedeIdentifier(sede: Pick<ISede, 'id'>): string {
    return sede.id;
  }

  compareSede(o1: Pick<ISede, 'id'> | null, o2: Pick<ISede, 'id'> | null): boolean {
    return o1 && o2 ? this.getSedeIdentifier(o1) === this.getSedeIdentifier(o2) : o1 === o2;
  }

  addSedeToCollectionIfMissing<Type extends Pick<ISede, 'id'>>(
    sedeCollection: Type[],
    ...sedesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sedes: Type[] = sedesToCheck.filter(isPresent);
    if (sedes.length > 0) {
      const sedeCollectionIdentifiers = sedeCollection.map(sedeItem => this.getSedeIdentifier(sedeItem)!);
      const sedesToAdd = sedes.filter(sedeItem => {
        const sedeIdentifier = this.getSedeIdentifier(sedeItem);
        if (sedeCollectionIdentifiers.includes(sedeIdentifier)) {
          return false;
        }
        sedeCollectionIdentifiers.push(sedeIdentifier);
        return true;
      });
      return [...sedesToAdd, ...sedeCollection];
    }
    return sedeCollection;
  }

  protected convertDateFromClient<T extends ISede | NewSede | PartialUpdateSede>(sede: T): RestOf<T> {
    return {
      ...sede,
      fecAper: sede.fecAper?.toJSON() ?? null,
      fecCrea: sede.fecCrea?.toJSON() ?? null,
      fecModif: sede.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restSede: RestSede): ISede {
    return {
      ...restSede,
      fecAper: restSede.fecAper ? dayjs(restSede.fecAper) : undefined,
      fecCrea: restSede.fecCrea ? dayjs(restSede.fecCrea) : undefined,
      fecModif: restSede.fecModif ? dayjs(restSede.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSede>): HttpResponse<ISede> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSede[]>): HttpResponse<ISede[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
