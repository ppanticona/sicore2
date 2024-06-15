import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IPrecuenta } from '../precuenta.model';
import { PrecuentaService } from '../service/precuenta.service';

@Injectable({ providedIn: 'root' })
export class PrecuentaRoutingResolveService implements Resolve<IPrecuenta | null> {
  constructor(protected service: PrecuentaService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPrecuenta | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((precuenta: HttpResponse<IPrecuenta>) => {
          if (precuenta.body) {
            return of(precuenta.body);
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
