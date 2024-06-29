import { Component, OnInit } from '@angular/core';

import { AperturarService } from './aperturar.service';

@Component({
  selector: 'jhi-aperturar',
  templateUrl: './aperturar.component.html',
  styleUrls: ['./aperturar.component.scss'],
})
export class AperturarComponent implements OnInit {

  
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
    private aperturarService: AperturarService
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

    //lista cajas asignadas 01 estado activo
    this.aperturarService.listaAsignacionCajaxEstado("01")
        .subscribe((data: any)=>{
          setTimeout(()=>{
            console.log(data)
            this.cajasAsignadasActivas=data.listaCajasAsignadas
            this.loading=false;
          },500)

        })
    //lista cajas  01 estado activo que no se encuentren asignadas
    this.aperturarService.listaCajasxEstado("01")
        .subscribe((data: any)=>{
          console.log(data)
          this.cajasActivas=data.listaCajas
        })
    //lista empleados
    this.aperturarService.listaUsuariosPorRol("ROLE_CAJERO")
        .subscribe((data: any)=>{
          console.log(data)
          this.cajeros=data
        })
}
  aperturarCaja(forma:any){
    
    this.aperturarService.aperturarCaja(forma.value)
        .subscribe((data: any)=>{
          console.log(forma.value)
          this.cargaInicial();
         // window.location.reload();
        })
  }
}
