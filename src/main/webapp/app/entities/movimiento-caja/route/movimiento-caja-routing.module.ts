import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { MovimientoCajaComponent } from '../list/movimiento-caja.component';
import { MovimientoCajaDetailComponent } from '../detail/movimiento-caja-detail.component';
import { MovimientoCajaUpdateComponent } from '../update/movimiento-caja-update.component';
import { MovimientoCajaRoutingResolveService } from './movimiento-caja-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const movimientoCajaRoute: Routes = [
  {
    path: '',
    component: MovimientoCajaComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MovimientoCajaDetailComponent,
    resolve: {
      movimientoCaja: MovimientoCajaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MovimientoCajaUpdateComponent,
    resolve: {
      movimientoCaja: MovimientoCajaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MovimientoCajaUpdateComponent,
    resolve: {
      movimientoCaja: MovimientoCajaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(movimientoCajaRoute)],
  exports: [RouterModule],
})
export class MovimientoCajaRoutingModule {}
