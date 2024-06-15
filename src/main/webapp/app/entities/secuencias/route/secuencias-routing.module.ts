import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SecuenciasComponent } from '../list/secuencias.component';
import { SecuenciasDetailComponent } from '../detail/secuencias-detail.component';
import { SecuenciasUpdateComponent } from '../update/secuencias-update.component';
import { SecuenciasRoutingResolveService } from './secuencias-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const secuenciasRoute: Routes = [
  {
    path: '',
    component: SecuenciasComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SecuenciasDetailComponent,
    resolve: {
      secuencias: SecuenciasRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SecuenciasUpdateComponent,
    resolve: {
      secuencias: SecuenciasRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SecuenciasUpdateComponent,
    resolve: {
      secuencias: SecuenciasRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(secuenciasRoute)],
  exports: [RouterModule],
})
export class SecuenciasRoutingModule {}
