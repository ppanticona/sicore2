import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAutorizacion } from '../autorizacion.model';
import { AutorizacionService } from '../service/autorizacion.service';

@Injectable({ providedIn: 'root' })
export class AutorizacionRoutingResolveService implements Resolve<IAutorizacion | null> {
  constructor(protected service: AutorizacionService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAutorizacion | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((autorizacion: HttpResponse<IAutorizacion>) => {
          if (autorizacion.body) {
            return of(autorizacion.body);
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
