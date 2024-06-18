import { Route } from '@angular/router';

import { CerrarComponent } from './cerrar.component';

import { Authority } from 'app/config/authority.constants';

export const cerrarRoute: Route = 
  {
    path: 'cerrarCaja',
    component: CerrarComponent,
    data: {
      authorities: [  Authority.ADMIN],
      pageTitle: 'Cerrar  Caja',
    },
  }
;


