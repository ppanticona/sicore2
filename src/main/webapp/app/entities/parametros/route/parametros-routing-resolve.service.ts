import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IParametros } from '../parametros.model';
import { ParametrosService } from '../service/parametros.service';

@Injectable({ providedIn: 'root' })
export class ParametrosRoutingResolveService implements Resolve<IParametros | null> {
  constructor(protected service: ParametrosService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IParametros | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((parametros: HttpResponse<IParametros>) => {
          if (parametros.body) {
            return of(parametros.body);
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
