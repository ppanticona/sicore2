import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse,HttpHeaders } from '@angular/common/http';


// import { ROUT_SIMPL_STR } from '../../shared';

import { map } from 'rxjs/operators';

@Injectable()
export class AperturarService {

    listaAsignacionCajasxEstadoURL:string=`api/listarCajasAsignadasPorEstado/`
    listaCajasxEstadoURL:string=`api/listarCajasPorEstado/`
    listaCajasParaAsignarURL:string =`api/listarCajasParaAsignar`
    listaEmpleadosURL:string=`api/listarEmpleadosPorEstadoAndCategoria/` 
    listaUsuariosPorRolURL:string =`api/admin/usuariosByRol/`

    aperturarCajaURL:string=`api/aperturarCaja/`
    constructor(private http: HttpClient) {
    }

    listaAsignacionCajaxEstado(cod_estado:string){
      let url=this.listaAsignacionCajasxEstadoURL+cod_estado;
      return this.http.get(url)
                      .pipe(map(res=>res));
    }
    
    listaCajasxEstado(cod_estado:string){
      let url=this.listaCajasxEstadoURL+cod_estado;
      return this.http.get(url)
                      .pipe(map(res=>res));
    }
    listaCajasParaAsignar(){
      let url=this.listaCajasParaAsignarURL;
      return this.http.get(url)
                      .pipe(map(res=>res));
    }
    listaCajerosxEstado(cod_estado:string){
      let url=this.listaEmpleadosURL+cod_estado+"/ROLE_CAJERO";
      return this.http.get(url)
                      .pipe(map(res=>res));
    }
    listaUsuariosPorRol(rol:string){
      let url=this.listaUsuariosPorRolURL+rol;
      return this.http.get(url)
                      .pipe(map(res=>res));
    }
    aperturarCaja(asignacion:any[]){

        let body=JSON.stringify(asignacion);
        let headers =new HttpHeaders({
          'Content-Type':'application/json'
        });

        return this.http.post( this.aperturarCajaURL,body, {headers} )
                .pipe(map(res=>{

                  return res;
                }))

    }


    }
