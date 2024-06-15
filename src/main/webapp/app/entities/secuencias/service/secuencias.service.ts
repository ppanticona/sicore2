import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISecuencias, NewSecuencias } from '../secuencias.model';

export type PartialUpdateSecuencias = Partial<ISecuencias> & Pick<ISecuencias, 'id'>;

export type EntityResponseType = HttpResponse<ISecuencias>;
export type EntityArrayResponseType = HttpResponse<ISecuencias[]>;

@Injectable({ providedIn: 'root' })
export class SecuenciasService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/secuencias');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(secuencias: NewSecuencias): Observable<EntityResponseType> {
    return this.http.post<ISecuencias>(this.resourceUrl, secuencias, { observe: 'response' });
  }

  update(secuencias: ISecuencias): Observable<EntityResponseType> {
    return this.http.put<ISecuencias>(`${this.resourceUrl}/${this.getSecuenciasIdentifier(secuencias)}`, secuencias, {
      observe: 'response',
    });
  }

  partialUpdate(secuencias: PartialUpdateSecuencias): Observable<EntityResponseType> {
    return this.http.patch<ISecuencias>(`${this.resourceUrl}/${this.getSecuenciasIdentifier(secuencias)}`, secuencias, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<ISecuencias>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISecuencias[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSecuenciasIdentifier(secuencias: Pick<ISecuencias, 'id'>): string {
    return secuencias.id;
  }

  compareSecuencias(o1: Pick<ISecuencias, 'id'> | null, o2: Pick<ISecuencias, 'id'> | null): boolean {
    return o1 && o2 ? this.getSecuenciasIdentifier(o1) === this.getSecuenciasIdentifier(o2) : o1 === o2;
  }

  addSecuenciasToCollectionIfMissing<Type extends Pick<ISecuencias, 'id'>>(
    secuenciasCollection: Type[],
    ...secuenciasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const secuencias: Type[] = secuenciasToCheck.filter(isPresent);
    if (secuencias.length > 0) {
      const secuenciasCollectionIdentifiers = secuenciasCollection.map(secuenciasItem => this.getSecuenciasIdentifier(secuenciasItem)!);
      const secuenciasToAdd = secuencias.filter(secuenciasItem => {
        const secuenciasIdentifier = this.getSecuenciasIdentifier(secuenciasItem);
        if (secuenciasCollectionIdentifiers.includes(secuenciasIdentifier)) {
          return false;
        }
        secuenciasCollectionIdentifiers.push(secuenciasIdentifier);
        return true;
      });
      return [...secuenciasToAdd, ...secuenciasCollection];
    }
    return secuenciasCollection;
  }
}
