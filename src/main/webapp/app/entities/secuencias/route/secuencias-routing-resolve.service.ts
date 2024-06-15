import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISecuencias } from '../secuencias.model';
import { SecuenciasService } from '../service/secuencias.service';

@Injectable({ providedIn: 'root' })
export class SecuenciasRoutingResolveService implements Resolve<ISecuencias | null> {
  constructor(protected service: SecuenciasService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISecuencias | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((secuencias: HttpResponse<ISecuencias>) => {
          if (secuencias.body) {
            return of(secuencias.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
