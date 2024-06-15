import { Route } from '@angular/router';

import { MesasComponent } from './mesas.component';

import { Authority } from 'app/config/authority.constants';

export const mesasRoute: Route = 
  {
    path: 'puntoventa',
    component: MesasComponent,
    data: {
      authorities: [Authority.CAJERO, Authority.ADMIN],
      pageTitle: 'Punto de venta mesas',
    },
  }
;


