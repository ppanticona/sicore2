import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DetallePrecuentaComponent } from './list/detalle-precuenta.component';
import { DetallePrecuentaDetailComponent } from './detail/detalle-precuenta-detail.component';
import { DetallePrecuentaUpdateComponent } from './update/detalle-precuenta-update.component';
import { DetallePrecuentaDeleteDialogComponent } from './delete/detalle-precuenta-delete-dialog.component';
import { DetallePrecuentaRoutingModule } from './route/detalle-precuenta-routing.module';

@NgModule({
  imports: [SharedModule, DetallePrecuentaRoutingModule],
  declarations: [
    DetallePrecuentaComponent,
    DetallePrecuentaDetailComponent,
    DetallePrecuentaUpdateComponent,
    DetallePrecuentaDeleteDialogComponent,
  ],
})
export class DetallePrecuentaModule {}
