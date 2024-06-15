import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDetalleOrden } from '../detalle-orden.model';
import { DetalleOrdenService } from '../service/detalle-orden.service';

@Injectable({ providedIn: 'root' })
export class DetalleOrdenRoutingResolveService implements Resolve<IDetalleOrden | null> {
  constructor(protected service: DetalleOrdenService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDetalleOrden | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((detalleOrden: HttpResponse<IDetalleOrden>) => {
          if (detalleOrden.body) {
            return of(detalleOrden.body);
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
