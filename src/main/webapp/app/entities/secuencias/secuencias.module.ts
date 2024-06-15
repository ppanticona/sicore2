import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SecuenciasComponent } from './list/secuencias.component';
import { SecuenciasDetailComponent } from './detail/secuencias-detail.component';
import { SecuenciasUpdateComponent } from './update/secuencias-update.component';
import { SecuenciasDeleteDialogComponent } from './delete/secuencias-delete-dialog.component';
import { SecuenciasRoutingModule } from './route/secuencias-routing.module';

@NgModule({
  imports: [SharedModule, SecuenciasRoutingModule],
  declarations: [SecuenciasComponent, SecuenciasDetailComponent, SecuenciasUpdateComponent, SecuenciasDeleteDialogComponent],
})
export class SecuenciasModule {}
