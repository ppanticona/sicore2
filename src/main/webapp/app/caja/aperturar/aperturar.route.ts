import { Route } from '@angular/router';

import { AperturarComponent } from './aperturar.component';

import { Authority } from 'app/config/authority.constants';

export const aperturarRoute: Route = 
  {
    path: 'aperturarCaja',
    component: AperturarComponent,
    data: {
      authorities: [Authority.CAJERO, Authority.ADMIN],
      pageTitle: 'Aperturar  Caja',
    },
  }
;


