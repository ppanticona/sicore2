import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EmpleadosComponent } from '../list/empleados.component';
import { EmpleadosDetailComponent } from '../detail/empleados-detail.component';
import { EmpleadosUpdateComponent } from '../update/empleados-update.component';
import { EmpleadosRoutingResolveService } from './empleados-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const empleadosRoute: Routes = [
  {
    path: '',
    component: EmpleadosComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EmpleadosDetailComponent,
    resolve: {
      empleados: EmpleadosRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EmpleadosUpdateComponent,
    resolve: {
      empleados: EmpleadosRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EmpleadosUpdateComponent,
    resolve: {
      empleados: EmpleadosRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(empleadosRoute)],
  exports: [RouterModule],
})
export class EmpleadosRoutingModule {}
