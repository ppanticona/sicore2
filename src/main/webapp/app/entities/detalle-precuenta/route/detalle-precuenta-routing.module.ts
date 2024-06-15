import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DetallePrecuentaComponent } from '../list/detalle-precuenta.component';
import { DetallePrecuentaDetailComponent } from '../detail/detalle-precuenta-detail.component';
import { DetallePrecuentaUpdateComponent } from '../update/detalle-precuenta-update.component';
import { DetallePrecuentaRoutingResolveService } from './detalle-precuenta-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const detallePrecuentaRoute: Routes = [
  {
    path: '',
    component: DetallePrecuentaComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DetallePrecuentaDetailComponent,
    resolve: {
      detallePrecuenta: DetallePrecuentaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DetallePrecuentaUpdateComponent,
    resolve: {
      detallePrecuenta: DetallePrecuentaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DetallePrecuentaUpdateComponent,
    resolve: {
      detallePrecuenta: DetallePrecuentaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(detallePrecuentaRoute)],
  exports: [RouterModule],
})
export class DetallePrecuentaRoutingModule {}
