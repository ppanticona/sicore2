import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { RegVentaComponent } from './list/reg-venta.component';
import { RegVentaDetailComponent } from './detail/reg-venta-detail.component';
import { RegVentaUpdateComponent } from './update/reg-venta-update.component';
import { RegVentaDeleteDialogComponent } from './delete/reg-venta-delete-dialog.component';
import { RegVentaRoutingModule } from './route/reg-venta-routing.module';

@NgModule({
  imports: [SharedModule, RegVentaRoutingModule],
  declarations: [RegVentaComponent, RegVentaDetailComponent, RegVentaUpdateComponent, RegVentaDeleteDialogComponent],
})
export class RegVentaModule {}
