import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISede } from '../sede.model';
import { SedeService } from '../service/sede.service';

@Injectable({ providedIn: 'root' })
export class SedeRoutingResolveService implements Resolve<ISede | null> {
  constructor(protected service: SedeService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISede | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sede: HttpResponse<ISede>) => {
          if (sede.body) {
            return of(sede.body);
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
