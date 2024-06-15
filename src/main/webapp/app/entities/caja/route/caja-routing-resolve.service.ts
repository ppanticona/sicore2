import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICaja } from '../caja.model';
import { CajaService } from '../service/caja.service';

@Injectable({ providedIn: 'root' })
export class CajaRoutingResolveService implements Resolve<ICaja | null> {
  constructor(protected service: CajaService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICaja | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((caja: HttpResponse<ICaja>) => {
          if (caja.body) {
            return of(caja.body);
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
