import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAsignacionCaja } from '../asignacion-caja.model';
import { AsignacionCajaService } from '../service/asignacion-caja.service';

@Injectable({ providedIn: 'root' })
export class AsignacionCajaRoutingResolveService implements Resolve<IAsignacionCaja | null> {
  constructor(protected service: AsignacionCajaService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAsignacionCaja | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((asignacionCaja: HttpResponse<IAsignacionCaja>) => {
          if (asignacionCaja.body) {
            return of(asignacionCaja.body);
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
