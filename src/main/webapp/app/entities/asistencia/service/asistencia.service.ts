import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAsistencia, NewAsistencia } from '../asistencia.model';

export type PartialUpdateAsistencia = Partial<IAsistencia> & Pick<IAsistencia, 'id'>;

type RestOf<T extends IAsistencia | NewAsistencia> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestAsistencia = RestOf<IAsistencia>;

export type NewRestAsistencia = RestOf<NewAsistencia>;

export type PartialUpdateRestAsistencia = RestOf<PartialUpdateAsistencia>;

export type EntityResponseType = HttpResponse<IAsistencia>;
export type EntityArrayResponseType = HttpResponse<IAsistencia[]>;

@Injectable({ providedIn: 'root' })
export class AsistenciaService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/asistencias');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(asistencia: NewAsistencia): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(asistencia);
    return this.http
      .post<RestAsistencia>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(asistencia: IAsistencia): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(asistencia);
    return this.http
      .put<RestAsistencia>(`${this.resourceUrl}/${this.getAsistenciaIdentifier(asistencia)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(asistencia: PartialUpdateAsistencia): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(asistencia);
    return this.http
      .patch<RestAsistencia>(`${this.resourceUrl}/${this.getAsistenciaIdentifier(asistencia)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestAsistencia>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAsistencia[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAsistenciaIdentifier(asistencia: Pick<IAsistencia, 'id'>): string {
    return asistencia.id;
  }

  compareAsistencia(o1: Pick<IAsistencia, 'id'> | null, o2: Pick<IAsistencia, 'id'> | null): boolean {
    return o1 && o2 ? this.getAsistenciaIdentifier(o1) === this.getAsistenciaIdentifier(o2) : o1 === o2;
  }

  addAsistenciaToCollectionIfMissing<Type extends Pick<IAsistencia, 'id'>>(
    asistenciaCollection: Type[],
    ...asistenciasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const asistencias: Type[] = asistenciasToCheck.filter(isPresent);
    if (asistencias.length > 0) {
      const asistenciaCollectionIdentifiers = asistenciaCollection.map(asistenciaItem => this.getAsistenciaIdentifier(asistenciaItem)!);
      const asistenciasToAdd = asistencias.filter(asistenciaItem => {
        const asistenciaIdentifier = this.getAsistenciaIdentifier(asistenciaItem);
        if (asistenciaCollectionIdentifiers.includes(asistenciaIdentifier)) {
          return false;
        }
        asistenciaCollectionIdentifiers.push(asistenciaIdentifier);
        return true;
      });
      return [...asistenciasToAdd, ...asistenciaCollection];
    }
    return asistenciaCollection;
  }

  protected convertDateFromClient<T extends IAsistencia | NewAsistencia | PartialUpdateAsistencia>(asistencia: T): RestOf<T> {
    return {
      ...asistencia,
      fecCrea: asistencia.fecCrea?.toJSON() ?? null,
      fecModif: asistencia.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restAsistencia: RestAsistencia): IAsistencia {
    return {
      ...restAsistencia,
      fecCrea: restAsistencia.fecCrea ? dayjs(restAsistencia.fecCrea) : undefined,
      fecModif: restAsistencia.fecModif ? dayjs(restAsistencia.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAsistencia>): HttpResponse<IAsistencia> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAsistencia[]>): HttpResponse<IAsistencia[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
