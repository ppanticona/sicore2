import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DetalleOrdenComponent } from './list/detalle-orden.component';
import { DetalleOrdenDetailComponent } from './detail/detalle-orden-detail.component';
import { DetalleOrdenUpdateComponent } from './update/detalle-orden-update.component';
import { DetalleOrdenDeleteDialogComponent } from './delete/detalle-orden-delete-dialog.component';
import { DetalleOrdenRoutingModule } from './route/detalle-orden-routing.module';

@NgModule({
  imports: [SharedModule, DetalleOrdenRoutingModule],
  declarations: [DetalleOrdenComponent, DetalleOrdenDetailComponent, DetalleOrdenUpdateComponent, DetalleOrdenDeleteDialogComponent],
})
export class DetalleOrdenModule {}
