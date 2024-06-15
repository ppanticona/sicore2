
import { Authority } from 'app/config/authority.constants';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';


import {mesasRoute} from './mesas/mesas.route'; 


const PUNTOVENTA_ROUTES = [
  mesasRoute, 
 // movimientoRoute,
 // tcambioRoute
];
export const PUNTOVENTA_ROUTE: Routes = [
  {
    path: '',
    data: {
      authorities: [Authority.CAJERO, Authority.ADMIN],
      pageTitle: ' PUNTOS VENTA ',
    },
    children: PUNTOVENTA_ROUTES
  },
];
