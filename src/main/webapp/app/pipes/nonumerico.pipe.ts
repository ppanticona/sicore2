
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'nonumerico'
})
export class NonumericoPipe implements PipeTransform {

  transform(value: number, args?: any[]):any {



    if(isNaN(value)){

          return 0;
    }else{
      if(value<0){

          return 0;
      }else{
          return value;
      }

    }


  }

}
