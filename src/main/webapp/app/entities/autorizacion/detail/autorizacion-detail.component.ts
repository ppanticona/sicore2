import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAutorizacion } from '../autorizacion.model';

@Component({
  selector: 'jhi-autorizacion-detail',
  templateUrl: './autorizacion-detail.component.html',
})
export class AutorizacionDetailComponent implements OnInit {
  autorizacion: IAutorizacion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autorizacion }) => {
      this.autorizacion = autorizacion;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
