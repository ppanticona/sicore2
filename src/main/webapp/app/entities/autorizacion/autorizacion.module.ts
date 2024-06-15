import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AutorizacionComponent } from './list/autorizacion.component';
import { AutorizacionDetailComponent } from './detail/autorizacion-detail.component';
import { AutorizacionUpdateComponent } from './update/autorizacion-update.component';
import { AutorizacionDeleteDialogComponent } from './delete/autorizacion-delete-dialog.component';
import { AutorizacionRoutingModule } from './route/autorizacion-routing.module';

@NgModule({
  imports: [SharedModule, AutorizacionRoutingModule],
  declarations: [AutorizacionComponent, AutorizacionDetailComponent, AutorizacionUpdateComponent, AutorizacionDeleteDialogComponent],
})
export class AutorizacionModule {}
