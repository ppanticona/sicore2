import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DetalleOrdenComponent } from '../list/detalle-orden.component';
import { DetalleOrdenDetailComponent } from '../detail/detalle-orden-detail.component';
import { DetalleOrdenUpdateComponent } from '../update/detalle-orden-update.component';
import { DetalleOrdenRoutingResolveService } from './detalle-orden-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const detalleOrdenRoute: Routes = [
  {
    path: '',
    component: DetalleOrdenComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DetalleOrdenDetailComponent,
    resolve: {
      detalleOrden: DetalleOrdenRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DetalleOrdenUpdateComponent,
    resolve: {
      detalleOrden: DetalleOrdenRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DetalleOrdenUpdateComponent,
    resolve: {
      detalleOrden: DetalleOrdenRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(detalleOrdenRoute)],
  exports: [RouterModule],
})
export class DetalleOrdenRoutingModule {}
