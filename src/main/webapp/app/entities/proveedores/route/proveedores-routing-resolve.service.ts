import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IProveedores } from '../proveedores.model';
import { ProveedoresService } from '../service/proveedores.service';

@Injectable({ providedIn: 'root' })
export class ProveedoresRoutingResolveService implements Resolve<IProveedores | null> {
  constructor(protected service: ProveedoresService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProveedores | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((proveedores: HttpResponse<IProveedores>) => {
          if (proveedores.body) {
            return of(proveedores.body);
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
