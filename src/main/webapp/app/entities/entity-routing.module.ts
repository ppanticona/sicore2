import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'mesas',
        data: { pageTitle: 'sicore2App.mesas.home.title' },
        loadChildren: () => import('./mesas/mesas.module').then(m => m.MesasModule),
      },
      {
        path: 'proveedores',
        data: { pageTitle: 'sicore2App.proveedores.home.title' },
        loadChildren: () => import('./proveedores/proveedores.module').then(m => m.ProveedoresModule),
      },
      {
        path: 'clientes',
        data: { pageTitle: 'sicore2App.clientes.home.title' },
        loadChildren: () => import('./clientes/clientes.module').then(m => m.ClientesModule),
      },
      {
        path: 'empleados',
        data: { pageTitle: 'sicore2App.empleados.home.title' },
        loadChildren: () => import('./empleados/empleados.module').then(m => m.EmpleadosModule),
      },
      {
        path: 'reg-venta',
        data: { pageTitle: 'sicore2App.regVenta.home.title' },
        loadChildren: () => import('./reg-venta/reg-venta.module').then(m => m.RegVentaModule),
      },
      {
        path: 'reg-compras',
        data: { pageTitle: 'sicore2App.regCompras.home.title' },
        loadChildren: () => import('./reg-compras/reg-compras.module').then(m => m.RegComprasModule),
      },
      {
        path: 'movimiento-caja',
        data: { pageTitle: 'sicore2App.movimientoCaja.home.title' },
        loadChildren: () => import('./movimiento-caja/movimiento-caja.module').then(m => m.MovimientoCajaModule),
      },
      {
        path: 'autorizacion',
        data: { pageTitle: 'sicore2App.autorizacion.home.title' },
        loadChildren: () => import('./autorizacion/autorizacion.module').then(m => m.AutorizacionModule),
      },
      {
        path: 'caja',
        data: { pageTitle: 'sicore2App.caja.home.title' },
        loadChildren: () => import('./caja/caja.module').then(m => m.CajaModule),
      },
      {
        path: 'producto',
        data: { pageTitle: 'sicore2App.producto.home.title' },
        loadChildren: () => import('./producto/producto.module').then(m => m.ProductoModule),
      },
      {
        path: 'precuenta',
        data: { pageTitle: 'sicore2App.precuenta.home.title' },
        loadChildren: () => import('./precuenta/precuenta.module').then(m => m.PrecuentaModule),
      },
      {
        path: 'detalle-precuenta',
        data: { pageTitle: 'sicore2App.detallePrecuenta.home.title' },
        loadChildren: () => import('./detalle-precuenta/detalle-precuenta.module').then(m => m.DetallePrecuentaModule),
      },
      {
        path: 'orden',
        data: { pageTitle: 'sicore2App.orden.home.title' },
        loadChildren: () => import('./orden/orden.module').then(m => m.OrdenModule),
      },
      {
        path: 'detalle-orden',
        data: { pageTitle: 'sicore2App.detalleOrden.home.title' },
        loadChildren: () => import('./detalle-orden/detalle-orden.module').then(m => m.DetalleOrdenModule),
      },
      {
        path: 'secuencias',
        data: { pageTitle: 'sicore2App.secuencias.home.title' },
        loadChildren: () => import('./secuencias/secuencias.module').then(m => m.SecuenciasModule),
      },
      {
        path: 'parametros',
        data: { pageTitle: 'sicore2App.parametros.home.title' },
        loadChildren: () => import('./parametros/parametros.module').then(m => m.ParametrosModule),
      },
      {
        path: 'promocion',
        data: { pageTitle: 'sicore2App.promocion.home.title' },
        loadChildren: () => import('./promocion/promocion.module').then(m => m.PromocionModule),
      },
      {
        path: 'asistencia',
        data: { pageTitle: 'sicore2App.asistencia.home.title' },
        loadChildren: () => import('./asistencia/asistencia.module').then(m => m.AsistenciaModule),
      },
      {
        path: 'asignacion-caja',
        data: { pageTitle: 'sicore2App.asignacionCaja.home.title' },
        loadChildren: () => import('./asignacion-caja/asignacion-caja.module').then(m => m.AsignacionCajaModule),
      },
      {
        path: 'sede',
        data: { pageTitle: 'sicore2App.sede.home.title' },
        loadChildren: () => import('./sede/sede.module').then(m => m.SedeModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
