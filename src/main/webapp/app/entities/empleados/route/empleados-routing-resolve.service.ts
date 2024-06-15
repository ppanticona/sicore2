import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEmpleados } from '../empleados.model';
import { EmpleadosService } from '../service/empleados.service';

@Injectable({ providedIn: 'root' })
export class EmpleadosRoutingResolveService implements Resolve<IEmpleados | null> {
  constructor(protected service: EmpleadosService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEmpleados | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((empleados: HttpResponse<IEmpleados>) => {
          if (empleados.body) {
            return of(empleados.body);
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
