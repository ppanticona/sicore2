import { Component, OnInit } from '@angular/core';
 
import { NgbModalRef,NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CerrarService } from './cerrar.service';
import { AperturarService } from '../aperturar/aperturar.service';
import { JhiCerrarCajaModalComponent } from './cerrar-caja-modal.component';
@Component({
  selector: 'jhi-cerrar',
  templateUrl: './cerrar.component.html',
  styleUrls: ['./cerrar.component.scss'],
})
export class CerrarComponent implements OnInit {

   
  filter: string;
  orderProp: string;
  reverse: boolean;
  cajasAsignadasActivas: any[]=[];
  cajasActivas: any[]=[];
  loading:boolean =true; 
   
  mensaje: string="IniciO";
  detalleCaja:any={
    cod_asignacion: "",
    cod_caja: "",
    cod_emp_autoriza :"",
    obs_apertura:"",
    obsCierre:"",
    cod_estado: "",
    monto_inicial_soles: 0,
    monto_inicial_dolares: 0,
    fec_asignacion: "",
    monto_final_soles: 0,
    monto_final_dolares: 0,
    monto_final_visa: 0,
    cod_empleado: "",
    diferencia_soles:0,
    diferencia_dolares:0,
    nombres: "",
    apellidos: "",
    mrealSoles:0.00,
    mrealDolares:0.00
  }
  
  NroCaja:string="";
  mrealSoles:number=0;
  mrealDolares:number=0;
  cajeros:any[]=[];

  asignacion:any=[];


  constructor( 
    private cerrarService: CerrarService,
    private aperturarService: AperturarService,
    private modalService: NgbModal,
  ) {
      this.filter = '';
      this.orderProp = 'name';
      this.reverse = false;
      //obtengo las cajas en estado aperturada
      this.aperturarService.listaCajasxEstado("02")
          .subscribe((data:any)=>{
            console.log(data)
            this.cajasActivas=data.listaCajas
          })

          
  }

  activeTab: string = 'home';

  setActiveTab(tab: string): void {
    this.activeTab = tab;
  }

  ngOnInit() {

    this.listarCajas();
  }
  
  listarCajas(){
    console.log("iniciado nuevamente")
    this.cajasActivas=[];
    this.aperturarService.listaCajasxEstado("02")
        .subscribe((data:any)=>{
          console.log(data)
          this.cajasActivas=data.listaCajas
        })

  }

  verCaja(){
    
    this.cerrarService.obtieneDatosCajaAsignada(this.NroCaja)
    .subscribe((data:any)=>{
      console.log(data)
      this.detalleCaja=data
    })
  }

  restarSoles(){

  }
  cerrarCaja(){



    //asignamos valores a diferencias soles y dolares

    if (this.detalleCaja.saldoActualSoles>0){
      this.detalleCaja.diferencia_soles=this.detalleCaja.saldoActualSoles-this.detalleCaja.mrealSoles;
    }else{
      this.detalleCaja.diferencia_soles=0;
      this.detalleCaja.saldoActualSoles=0;
    }
    if (this.detalleCaja.saldoActualDolares>0){
      this.detalleCaja.diferencia_dolares=this.detalleCaja.saldoActualDolares-this.detalleCaja.mrealDolares;

    }else{
      this.detalleCaja.diferencia_dolares=0;
      this.detalleCaja.saldoActualDolares=0;
    }
    console.log(this.detalleCaja);

    const modalRef  = this.modalService.open(JhiCerrarCajaModalComponent);
    modalRef.componentInstance.datos = this.detalleCaja;
    modalRef.closed.subscribe(reason => {
      if (reason === 'registrado') {
        this.listarCajas();
        this.detalleCaja={};
        this.mensaje="REGISTRADO"
      }
    });


  }
}
