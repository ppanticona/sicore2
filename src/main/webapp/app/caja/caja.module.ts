import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'app/shared/shared.module';
import { CAJAMODULE_ROUTE } from './caja.route'; 
import { DecimalPipe,DatePipe } from '@angular/common';
 
import { IslaComponent } from './isla/isla.component'; 
import { AperturarComponent } from './aperturar/aperturar.component'; 
import { CerrarComponent } from './cerrar/cerrar.component'; 

import { IslaService  } from './isla/isla.service'; 
import { AperturarService  } from './aperturar/aperturar.service'; 
import { CerrarService  } from './cerrar/cerrar.service';
@NgModule({
  imports: [
    SharedModule, 
    RouterModule.forChild(CAJAMODULE_ROUTE)],
  declarations: [ 
    AperturarComponent,
    IslaComponent,
    CerrarComponent,
    
  ],
  entryComponents: [ ],
  providers: [
    IslaService, 
    AperturarService,
    CerrarService,
    DecimalPipe,
    DatePipe

],
})
export class CajaModule {}
