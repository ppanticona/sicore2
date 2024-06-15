import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { MesasComponent } from '../list/mesas.component';
import { MesasDetailComponent } from '../detail/mesas-detail.component';
import { MesasUpdateComponent } from '../update/mesas-update.component';
import { MesasRoutingResolveService } from './mesas-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const mesasRoute: Routes = [
  {
    path: '',
    component: MesasComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MesasDetailComponent,
    resolve: {
      mesas: MesasRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MesasUpdateComponent,
    resolve: {
      mesas: MesasRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MesasUpdateComponent,
    resolve: {
      mesas: MesasRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(mesasRoute)],
  exports: [RouterModule],
})
export class MesasRoutingModule {}
