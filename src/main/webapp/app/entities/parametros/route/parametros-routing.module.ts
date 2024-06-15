import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ParametrosComponent } from '../list/parametros.component';
import { ParametrosDetailComponent } from '../detail/parametros-detail.component';
import { ParametrosUpdateComponent } from '../update/parametros-update.component';
import { ParametrosRoutingResolveService } from './parametros-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const parametrosRoute: Routes = [
  {
    path: '',
    component: ParametrosComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ParametrosDetailComponent,
    resolve: {
      parametros: ParametrosRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ParametrosUpdateComponent,
    resolve: {
      parametros: ParametrosRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ParametrosUpdateComponent,
    resolve: {
      parametros: ParametrosRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(parametrosRoute)],
  exports: [RouterModule],
})
export class ParametrosRoutingModule {}
