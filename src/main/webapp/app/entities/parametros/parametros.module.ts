import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ParametrosComponent } from './list/parametros.component';
import { ParametrosDetailComponent } from './detail/parametros-detail.component';
import { ParametrosUpdateComponent } from './update/parametros-update.component';
import { ParametrosDeleteDialogComponent } from './delete/parametros-delete-dialog.component';
import { ParametrosRoutingModule } from './route/parametros-routing.module';

@NgModule({
  imports: [SharedModule, ParametrosRoutingModule],
  declarations: [ParametrosComponent, ParametrosDetailComponent, ParametrosUpdateComponent, ParametrosDeleteDialogComponent],
})
export class ParametrosModule {}
