import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { RegVentaComponent } from '../list/reg-venta.component';
import { RegVentaDetailComponent } from '../detail/reg-venta-detail.component';
import { RegVentaUpdateComponent } from '../update/reg-venta-update.component';
import { RegVentaRoutingResolveService } from './reg-venta-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const regVentaRoute: Routes = [
  {
    path: '',
    component: RegVentaComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RegVentaDetailComponent,
    resolve: {
      regVenta: RegVentaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RegVentaUpdateComponent,
    resolve: {
      regVenta: RegVentaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RegVentaUpdateComponent,
    resolve: {
      regVenta: RegVentaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(regVentaRoute)],
  exports: [RouterModule],
})
export class RegVentaRoutingModule {}
