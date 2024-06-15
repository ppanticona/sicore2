import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SedeComponent } from '../list/sede.component';
import { SedeDetailComponent } from '../detail/sede-detail.component';
import { SedeUpdateComponent } from '../update/sede-update.component';
import { SedeRoutingResolveService } from './sede-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const sedeRoute: Routes = [
  {
    path: '',
    component: SedeComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SedeDetailComponent,
    resolve: {
      sede: SedeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SedeUpdateComponent,
    resolve: {
      sede: SedeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SedeUpdateComponent,
    resolve: {
      sede: SedeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sedeRoute)],
  exports: [RouterModule],
})
export class SedeRoutingModule {}
