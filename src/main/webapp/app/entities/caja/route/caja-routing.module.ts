import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CajaComponent } from '../list/caja.component';
import { CajaDetailComponent } from '../detail/caja-detail.component';
import { CajaUpdateComponent } from '../update/caja-update.component';
import { CajaRoutingResolveService } from './caja-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const cajaRoute: Routes = [
  {
    path: '',
    component: CajaComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CajaDetailComponent,
    resolve: {
      caja: CajaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CajaUpdateComponent,
    resolve: {
      caja: CajaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CajaUpdateComponent,
    resolve: {
      caja: CajaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(cajaRoute)],
  exports: [RouterModule],
})
export class CajaRoutingModule {}
