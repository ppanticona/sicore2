import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { PrecuentaComponent } from './list/precuenta.component';
import { PrecuentaDetailComponent } from './detail/precuenta-detail.component';
import { PrecuentaUpdateComponent } from './update/precuenta-update.component';
import { PrecuentaDeleteDialogComponent } from './delete/precuenta-delete-dialog.component';
import { PrecuentaRoutingModule } from './route/precuenta-routing.module';

@NgModule({
  imports: [SharedModule, PrecuentaRoutingModule],
  declarations: [PrecuentaComponent, PrecuentaDetailComponent, PrecuentaUpdateComponent, PrecuentaDeleteDialogComponent],
})
export class PrecuentaModule {}
