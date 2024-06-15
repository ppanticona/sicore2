import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { MovimientoCajaComponent } from './list/movimiento-caja.component';
import { MovimientoCajaDetailComponent } from './detail/movimiento-caja-detail.component';
import { MovimientoCajaUpdateComponent } from './update/movimiento-caja-update.component';
import { MovimientoCajaDeleteDialogComponent } from './delete/movimiento-caja-delete-dialog.component';
import { MovimientoCajaRoutingModule } from './route/movimiento-caja-routing.module';

@NgModule({
  imports: [SharedModule, MovimientoCajaRoutingModule],
  declarations: [
    MovimientoCajaComponent,
    MovimientoCajaDetailComponent,
    MovimientoCajaUpdateComponent,
    MovimientoCajaDeleteDialogComponent,
  ],
})
export class MovimientoCajaModule {}
