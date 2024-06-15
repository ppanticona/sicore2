import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IRegVenta } from '../reg-venta.model';
import { RegVentaService } from '../service/reg-venta.service';

@Injectable({ providedIn: 'root' })
export class RegVentaRoutingResolveService implements Resolve<IRegVenta | null> {
  constructor(protected service: RegVentaService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRegVenta | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((regVenta: HttpResponse<IRegVenta>) => {
          if (regVenta.body) {
            return of(regVenta.body);
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
