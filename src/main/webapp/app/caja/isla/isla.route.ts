import { Route } from '@angular/router';

import { IslaComponent } from './isla.component';

import { Authority } from 'app/config/authority.constants';

export const islaRoute: Route = 
  {
    path: 'isla',
    component: IslaComponent,
    data: {
      authorities: [Authority.CAJERO, Authority.ADMIN],
      pageTitle: 'Isla',
    },
  }
;


