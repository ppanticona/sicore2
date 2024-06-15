import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { RegComprasComponent } from './list/reg-compras.component';
import { RegComprasDetailComponent } from './detail/reg-compras-detail.component';
import { RegComprasUpdateComponent } from './update/reg-compras-update.component';
import { RegComprasDeleteDialogComponent } from './delete/reg-compras-delete-dialog.component';
import { RegComprasRoutingModule } from './route/reg-compras-routing.module';

@NgModule({
  imports: [SharedModule, RegComprasRoutingModule],
  declarations: [RegComprasComponent, RegComprasDetailComponent, RegComprasUpdateComponent, RegComprasDeleteDialogComponent],
})
export class RegComprasModule {}
