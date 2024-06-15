import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { RegComprasComponent } from '../list/reg-compras.component';
import { RegComprasDetailComponent } from '../detail/reg-compras-detail.component';
import { RegComprasUpdateComponent } from '../update/reg-compras-update.component';
import { RegComprasRoutingResolveService } from './reg-compras-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const regComprasRoute: Routes = [
  {
    path: '',
    component: RegComprasComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RegComprasDetailComponent,
    resolve: {
      regCompras: RegComprasRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RegComprasUpdateComponent,
    resolve: {
      regCompras: RegComprasRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RegComprasUpdateComponent,
    resolve: {
      regCompras: RegComprasRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(regComprasRoute)],
  exports: [RouterModule],
})
export class RegComprasRoutingModule {}
