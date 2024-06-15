import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { EmpleadosComponent } from './list/empleados.component';
import { EmpleadosDetailComponent } from './detail/empleados-detail.component';
import { EmpleadosUpdateComponent } from './update/empleados-update.component';
import { EmpleadosDeleteDialogComponent } from './delete/empleados-delete-dialog.component';
import { EmpleadosRoutingModule } from './route/empleados-routing.module';

@NgModule({
  imports: [SharedModule, EmpleadosRoutingModule],
  declarations: [EmpleadosComponent, EmpleadosDetailComponent, EmpleadosUpdateComponent, EmpleadosDeleteDialogComponent],
})
export class EmpleadosModule {}
