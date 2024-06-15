import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IMesas } from '../mesas.model';
import { MesasService } from '../service/mesas.service';

@Injectable({ providedIn: 'root' })
export class MesasRoutingResolveService implements Resolve<IMesas | null> {
  constructor(protected service: MesasService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMesas | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((mesas: HttpResponse<IMesas>) => {
          if (mesas.body) {
            return of(mesas.body);
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
