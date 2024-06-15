import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AsignacionCajaComponent } from './list/asignacion-caja.component';
import { AsignacionCajaDetailComponent } from './detail/asignacion-caja-detail.component';
import { AsignacionCajaUpdateComponent } from './update/asignacion-caja-update.component';
import { AsignacionCajaDeleteDialogComponent } from './delete/asignacion-caja-delete-dialog.component';
import { AsignacionCajaRoutingModule } from './route/asignacion-caja-routing.module';

@NgModule({
  imports: [SharedModule, AsignacionCajaRoutingModule],
  declarations: [
    AsignacionCajaComponent,
    AsignacionCajaDetailComponent,
    AsignacionCajaUpdateComponent,
    AsignacionCajaDeleteDialogComponent,
  ],
})
export class AsignacionCajaModule {}
