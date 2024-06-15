import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AutorizacionComponent } from '../list/autorizacion.component';
import { AutorizacionDetailComponent } from '../detail/autorizacion-detail.component';
import { AutorizacionUpdateComponent } from '../update/autorizacion-update.component';
import { AutorizacionRoutingResolveService } from './autorizacion-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const autorizacionRoute: Routes = [
  {
    path: '',
    component: AutorizacionComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AutorizacionDetailComponent,
    resolve: {
      autorizacion: AutorizacionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AutorizacionUpdateComponent,
    resolve: {
      autorizacion: AutorizacionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AutorizacionUpdateComponent,
    resolve: {
      autorizacion: AutorizacionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(autorizacionRoute)],
  exports: [RouterModule],
})
export class AutorizacionRoutingModule {}
