import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AsignacionCajaComponent } from '../list/asignacion-caja.component';
import { AsignacionCajaDetailComponent } from '../detail/asignacion-caja-detail.component';
import { AsignacionCajaUpdateComponent } from '../update/asignacion-caja-update.component';
import { AsignacionCajaRoutingResolveService } from './asignacion-caja-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const asignacionCajaRoute: Routes = [
  {
    path: '',
    component: AsignacionCajaComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AsignacionCajaDetailComponent,
    resolve: {
      asignacionCaja: AsignacionCajaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AsignacionCajaUpdateComponent,
    resolve: {
      asignacionCaja: AsignacionCajaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AsignacionCajaUpdateComponent,
    resolve: {
      asignacionCaja: AsignacionCajaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(asignacionCajaRoute)],
  exports: [RouterModule],
})
export class AsignacionCajaRoutingModule {}
