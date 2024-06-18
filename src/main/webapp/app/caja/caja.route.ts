
import { Authority } from 'app/config/authority.constants';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';


import {islaRoute} from './isla/isla.route'; 
import {aperturarRoute} from './aperturar/aperturar.route'; 
import {cerrarRoute} from './cerrar/cerrar.route'; 


const CAJA_ROUTES = [
  islaRoute, 
  aperturarRoute,
  cerrarRoute ,
 // movimientoRoute,
 // tcambioRoute
];
export const CAJAMODULE_ROUTE: Routes = [
  {
    path: '',
    data: {
      authorities: [Authority.CAJERO, Authority.ADMIN],
      pageTitle: '   ',
    },
    children: CAJA_ROUTES
  },
];
