import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEmpleados, NewEmpleados } from '../empleados.model';

export type PartialUpdateEmpleados = Partial<IEmpleados> & Pick<IEmpleados, 'id'>;

type RestOf<T extends IEmpleados | NewEmpleados> = Omit<T, 'fecIngreso' | 'fecNac' | 'fecCrea' | 'fecModif'> & {
  fecIngreso?: string | null;
  fecNac?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestEmpleados = RestOf<IEmpleados>;

export type NewRestEmpleados = RestOf<NewEmpleados>;

export type PartialUpdateRestEmpleados = RestOf<PartialUpdateEmpleados>;

export type EntityResponseType = HttpResponse<IEmpleados>;
export type EntityArrayResponseType = HttpResponse<IEmpleados[]>;

@Injectable({ providedIn: 'root' })
export class EmpleadosService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/empleados');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(empleados: NewEmpleados): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(empleados);
    return this.http
      .post<RestEmpleados>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(empleados: IEmpleados): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(empleados);
    return this.http
      .put<RestEmpleados>(`${this.resourceUrl}/${this.getEmpleadosIdentifier(empleados)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(empleados: PartialUpdateEmpleados): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(empleados);
    return this.http
      .patch<RestEmpleados>(`${this.resourceUrl}/${this.getEmpleadosIdentifier(empleados)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestEmpleados>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestEmpleados[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getEmpleadosIdentifier(empleados: Pick<IEmpleados, 'id'>): string {
    return empleados.id;
  }

  compareEmpleados(o1: Pick<IEmpleados, 'id'> | null, o2: Pick<IEmpleados, 'id'> | null): boolean {
    return o1 && o2 ? this.getEmpleadosIdentifier(o1) === this.getEmpleadosIdentifier(o2) : o1 === o2;
  }

  addEmpleadosToCollectionIfMissing<Type extends Pick<IEmpleados, 'id'>>(
    empleadosCollection: Type[],
    ...empleadosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const empleados: Type[] = empleadosToCheck.filter(isPresent);
    if (empleados.length > 0) {
      const empleadosCollectionIdentifiers = empleadosCollection.map(empleadosItem => this.getEmpleadosIdentifier(empleadosItem)!);
      const empleadosToAdd = empleados.filter(empleadosItem => {
        const empleadosIdentifier = this.getEmpleadosIdentifier(empleadosItem);
        if (empleadosCollectionIdentifiers.includes(empleadosIdentifier)) {
          return false;
        }
        empleadosCollectionIdentifiers.push(empleadosIdentifier);
        return true;
      });
      return [...empleadosToAdd, ...empleadosCollection];
    }
    return empleadosCollection;
  }

  protected convertDateFromClient<T extends IEmpleados | NewEmpleados | PartialUpdateEmpleados>(empleados: T): RestOf<T> {
    return {
      ...empleados,
      fecIngreso: empleados.fecIngreso?.toJSON() ?? null,
      fecNac: empleados.fecNac?.toJSON() ?? null,
      fecCrea: empleados.fecCrea?.toJSON() ?? null,
      fecModif: empleados.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restEmpleados: RestEmpleados): IEmpleados {
    return {
      ...restEmpleados,
      fecIngreso: restEmpleados.fecIngreso ? dayjs(restEmpleados.fecIngreso) : undefined,
      fecNac: restEmpleados.fecNac ? dayjs(restEmpleados.fecNac) : undefined,
      fecCrea: restEmpleados.fecCrea ? dayjs(restEmpleados.fecCrea) : undefined,
      fecModif: restEmpleados.fecModif ? dayjs(restEmpleados.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestEmpleados>): HttpResponse<IEmpleados> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestEmpleados[]>): HttpResponse<IEmpleados[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
