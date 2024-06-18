import { Component, OnInit } from '@angular/core';

import { IslaService } from './isla.service';

@Component({
  selector: 'jhi-isla',
  templateUrl: './isla.component.html',
  styleUrls: ['./isla.component.scss'],
})
export class IslaComponent implements OnInit {

  
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
  } 
}
