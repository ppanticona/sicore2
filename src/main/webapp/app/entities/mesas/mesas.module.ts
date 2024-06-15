import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { MesasComponent } from './list/mesas.component';
import { MesasDetailComponent } from './detail/mesas-detail.component';
import { MesasUpdateComponent } from './update/mesas-update.component';
import { MesasDeleteDialogComponent } from './delete/mesas-delete-dialog.component';
import { MesasRoutingModule } from './route/mesas-routing.module';

@NgModule({
  imports: [SharedModule, MesasRoutingModule],
  declarations: [MesasComponent, MesasDetailComponent, MesasUpdateComponent, MesasDeleteDialogComponent],
})
export class MesasModule {}
