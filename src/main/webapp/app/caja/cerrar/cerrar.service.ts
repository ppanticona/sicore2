import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse,HttpHeaders } from '@angular/common/http';


// import { ROUT_SIMPL_STR } from '../../shared';

import { map } from 'rxjs/operators';

@Injectable()
export class CerrarService {

  obtenerDatosCajaAsignadaURL:string=`/api/obtenerDatosCajaAsignada/`
  cerrarCajaURL:string=`/api/cerrarCaja/`
  constructor(private http: HttpClient) {
  }

  obtieneDatosCajaAsignada(nro_caja:string){
    let url=this.obtenerDatosCajaAsignadaURL+nro_caja;
    return this.http.get(url)
                    .pipe(map(res=>res));
  }


  cerrarCaja(asignacion:any[]){

      let body=JSON.stringify(asignacion);
      let headers =new HttpHeaders({
        'Content-Type':'application/json'
      });

      return this.http.post( this.cerrarCajaURL,body, {headers} )
              .pipe(map(res=>{

                return res;
              }))

  }


    }
