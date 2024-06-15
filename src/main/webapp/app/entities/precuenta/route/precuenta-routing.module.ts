import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { PrecuentaComponent } from '../list/precuenta.component';
import { PrecuentaDetailComponent } from '../detail/precuenta-detail.component';
import { PrecuentaUpdateComponent } from '../update/precuenta-update.component';
import { PrecuentaRoutingResolveService } from './precuenta-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const precuentaRoute: Routes = [
  {
    path: '',
    component: PrecuentaComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PrecuentaDetailComponent,
    resolve: {
      precuenta: PrecuentaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PrecuentaUpdateComponent,
    resolve: {
      precuenta: PrecuentaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PrecuentaUpdateComponent,
    resolve: {
      precuenta: PrecuentaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(precuentaRoute)],
  exports: [RouterModule],
})
export class PrecuentaRoutingModule {}
