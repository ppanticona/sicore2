import { Component, OnInit } from '@angular/core';

import { MesasService } from './mesas.service';

@Component({
  selector: 'jhi-mesas',
  templateUrl: './mesas.component.html',
  styleUrls: ['./mesas.component.scss'],
})
export class MesasComponent implements OnInit {

  
  errorTokenMensaje="";
  filter: string;
  orderProp: string;
  reverse: boolean;
  cajasAsignadasActivas: any[]=[];
  cajasActivas: any[]=[];
  loading:boolean =true;
  mesas:any[]=[];
  mesasSeleccionadas:any[]=[];
  mesaSeleccionada:any={};
  accionBoton="Valor Inicial";
  accionSolicitada:boolean=false;
  ingresaToken:boolean=false;
  token:string="";
   
  
  cajeros:any[]=[];

  asignacion:any=[];


  constructor(
      private mesasService: MesasService
  ) {
      this.filter = '';
      this.orderProp = 'name';
      this.reverse = false;
      this.cargaInicial();

          
  }

  activeTab: string = 'home';

  setActiveTab(tab: string): void {
    this.activeTab = tab;
  }

  ngOnInit() {

  }
  cargaInicial(){
 
      //lista cajas  01 estado activo que no se encuentren asignadas
      this.mesasService.listaMesasxEstado("01")
          .subscribe((data: any)=>{
            console.log(data)
            this.mesas=data.listaMesas
          })
      //lista empleados
      this.mesasService.listaCajerosxEstado("01")
          .subscribe((data: any)=>{
            console.log(data)
            this.cajeros=data.listaCajeros
          })
  }
  mostrarToken(){
    this.ingresaToken=true;
  }
  append(digito: string){
        this.token += digito;
  }
  aperturarCaja(forma:any){
    
    this.mesasService.aperturarCaja(forma.value)
        .subscribe((data: any)=>{
          console.log(forma.value)
          this.cargaInicial();
         // window.location.reload();
        })
  }

  updateChecked2(value:any,event:any,detalle:any){

    if(event.target.checked){
      this.mesasSeleccionadas.push(value);

      this.mesaSeleccionada=detalle;

    }
    else if (!event.target.checked){
      let indexx = this.mesasSeleccionadas.indexOf(value);
      this.mesasSeleccionadas.splice(indexx,1);
    }
 
  }


  procesarToken(){

    this.errorTokenMensaje=""; 
    //servicio de busqueda de acceso el result enviarlo en el dismiss




}


pin: string = '';

addDigit(digit: string): void {
  if (this.pin.length < 4) {
    this.pin += digit;

    if (this.pin.length === 4) {
      this.submitPin();
    }
  }
}

clearPin(): void {
  this.pin = '';
}

submitPin(): void {
  console.log('Submitted PIN:', this.pin);
  // Aquí puedes agregar la lógica para enviar el PIN al servidor

  this.clearPin();
}
getDisplay(): string[] {
  const display = this.pin.split('').map(() => '*');
  while (display.length < 4) {
    display.push('-');
  }
  return display;
}

}
