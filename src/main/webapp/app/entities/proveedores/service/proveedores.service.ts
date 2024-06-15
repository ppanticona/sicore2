import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IProveedores, NewProveedores } from '../proveedores.model';

export type PartialUpdateProveedores = Partial<IProveedores> & Pick<IProveedores, 'id'>;

type RestOf<T extends IProveedores | NewProveedores> = Omit<T, 'fecNac' | 'fecCrea' | 'fecModif'> & {
  fecNac?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestProveedores = RestOf<IProveedores>;

export type NewRestProveedores = RestOf<NewProveedores>;

export type PartialUpdateRestProveedores = RestOf<PartialUpdateProveedores>;

export type EntityResponseType = HttpResponse<IProveedores>;
export type EntityArrayResponseType = HttpResponse<IProveedores[]>;

@Injectable({ providedIn: 'root' })
export class ProveedoresService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/proveedores');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(proveedores: NewProveedores): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proveedores);
    return this.http
      .post<RestProveedores>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(proveedores: IProveedores): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proveedores);
    return this.http
      .put<RestProveedores>(`${this.resourceUrl}/${this.getProveedoresIdentifier(proveedores)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(proveedores: PartialUpdateProveedores): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proveedores);
    return this.http
      .patch<RestProveedores>(`${this.resourceUrl}/${this.getProveedoresIdentifier(proveedores)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestProveedores>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestProveedores[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getProveedoresIdentifier(proveedores: Pick<IProveedores, 'id'>): string {
    return proveedores.id;
  }

  compareProveedores(o1: Pick<IProveedores, 'id'> | null, o2: Pick<IProveedores, 'id'> | null): boolean {
    return o1 && o2 ? this.getProveedoresIdentifier(o1) === this.getProveedoresIdentifier(o2) : o1 === o2;
  }

  addProveedoresToCollectionIfMissing<Type extends Pick<IProveedores, 'id'>>(
    proveedoresCollection: Type[],
    ...proveedoresToCheck: (Type | null | undefined)[]
  ): Type[] {
    const proveedores: Type[] = proveedoresToCheck.filter(isPresent);
    if (proveedores.length > 0) {
      const proveedoresCollectionIdentifiers = proveedoresCollection.map(
        proveedoresItem => this.getProveedoresIdentifier(proveedoresItem)!
      );
      const proveedoresToAdd = proveedores.filter(proveedoresItem => {
        const proveedoresIdentifier = this.getProveedoresIdentifier(proveedoresItem);
        if (proveedoresCollectionIdentifiers.includes(proveedoresIdentifier)) {
          return false;
        }
        proveedoresCollectionIdentifiers.push(proveedoresIdentifier);
        return true;
      });
      return [...proveedoresToAdd, ...proveedoresCollection];
    }
    return proveedoresCollection;
  }

  protected convertDateFromClient<T extends IProveedores | NewProveedores | PartialUpdateProveedores>(proveedores: T): RestOf<T> {
    return {
      ...proveedores,
      fecNac: proveedores.fecNac?.toJSON() ?? null,
      fecCrea: proveedores.fecCrea?.toJSON() ?? null,
      fecModif: proveedores.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restProveedores: RestProveedores): IProveedores {
    return {
      ...restProveedores,
      fecNac: restProveedores.fecNac ? dayjs(restProveedores.fecNac) : undefined,
      fecCrea: restProveedores.fecCrea ? dayjs(restProveedores.fecCrea) : undefined,
      fecModif: restProveedores.fecModif ? dayjs(restProveedores.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestProveedores>): HttpResponse<IProveedores> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestProveedores[]>): HttpResponse<IProveedores[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
