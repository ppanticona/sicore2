import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IMovimientoCaja } from '../movimiento-caja.model';
import { MovimientoCajaService } from '../service/movimiento-caja.service';

@Injectable({ providedIn: 'root' })
export class MovimientoCajaRoutingResolveService implements Resolve<IMovimientoCaja | null> {
  constructor(protected service: MovimientoCajaService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMovimientoCaja | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((movimientoCaja: HttpResponse<IMovimientoCaja>) => {
          if (movimientoCaja.body) {
            return of(movimientoCaja.body);
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
