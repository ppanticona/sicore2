import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'app/shared/shared.module';
import { PUNTOVENTA_ROUTE } from './puntoventa.route';
import { MesasComponent } from './mesas/mesas.component'; 
import { DecimalPipe,DatePipe } from '@angular/common';

import { MesasService  } from './mesas/mesas.service';
@NgModule({
  imports: [
    SharedModule, 
    RouterModule.forChild(PUNTOVENTA_ROUTE)],
  declarations: [
    MesasComponent,  
  ],
  entryComponents: [ ],
  providers: [
    MesasService, 
    DecimalPipe,
    DatePipe

],
})
export class PuntoventaModule {}
