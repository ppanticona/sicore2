import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IParametros, NewParametros } from '../parametros.model';

export type PartialUpdateParametros = Partial<IParametros> & Pick<IParametros, 'id'>;

export type EntityResponseType = HttpResponse<IParametros>;
export type EntityArrayResponseType = HttpResponse<IParametros[]>;

@Injectable({ providedIn: 'root' })
export class ParametrosService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/parametros');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(parametros: NewParametros): Observable<EntityResponseType> {
    return this.http.post<IParametros>(this.resourceUrl, parametros, { observe: 'response' });
  }

  update(parametros: IParametros): Observable<EntityResponseType> {
    return this.http.put<IParametros>(`${this.resourceUrl}/${this.getParametrosIdentifier(parametros)}`, parametros, {
      observe: 'response',
    });
  }

  partialUpdate(parametros: PartialUpdateParametros): Observable<EntityResponseType> {
    return this.http.patch<IParametros>(`${this.resourceUrl}/${this.getParametrosIdentifier(parametros)}`, parametros, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IParametros>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IParametros[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getParametrosIdentifier(parametros: Pick<IParametros, 'id'>): string {
    return parametros.id;
  }

  compareParametros(o1: Pick<IParametros, 'id'> | null, o2: Pick<IParametros, 'id'> | null): boolean {
    return o1 && o2 ? this.getParametrosIdentifier(o1) === this.getParametrosIdentifier(o2) : o1 === o2;
  }

  addParametrosToCollectionIfMissing<Type extends Pick<IParametros, 'id'>>(
    parametrosCollection: Type[],
    ...parametrosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const parametros: Type[] = parametrosToCheck.filter(isPresent);
    if (parametros.length > 0) {
      const parametrosCollectionIdentifiers = parametrosCollection.map(parametrosItem => this.getParametrosIdentifier(parametrosItem)!);
      const parametrosToAdd = parametros.filter(parametrosItem => {
        const parametrosIdentifier = this.getParametrosIdentifier(parametrosItem);
        if (parametrosCollectionIdentifiers.includes(parametrosIdentifier)) {
          return false;
        }
        parametrosCollectionIdentifiers.push(parametrosIdentifier);
        return true;
      });
      return [...parametrosToAdd, ...parametrosCollection];
    }
    return parametrosCollection;
  }
}
