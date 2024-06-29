import { Component, OnInit } from '@angular/core';
import { NgbActiveModal,NgbModal,NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

import { CerrarService } from './cerrar.service';

@Component({
    templateUrl: './cerrar-caja-modal.component.html'
})
export class JhiCerrarCajaModalComponent implements OnInit {


    datos:any;
    flagError:boolean=false;
    textoError:String="";
    constructor(
                  private cerrarService: CerrarService,
                  public activeModal: NgbActiveModal
                  ) {

                  }

    ngOnInit() {
      this.flagError=false;
    }

    clear() {
        this.activeModal.close('cancel');
    }

    confirmarCierre(){
      this.flagError=false;
      this.cerrarService.cerrarCaja(this.datos)
      .subscribe((data:any)=>{
        if(data.Result=="OK"){
          this.activeModal.close('registrado');
          this.flagError=false;
        }else if(data.Result=="ERROR"){
          this.flagError=true;
          this.textoError=data.Message;
        }

      })
    }

}
