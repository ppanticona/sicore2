import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAsignacionCaja } from '../asignacion-caja.model';

@Component({
  selector: 'jhi-asignacion-caja-detail',
  templateUrl: './asignacion-caja-detail.component.html',
})
export class AsignacionCajaDetailComponent implements OnInit {
  asignacionCaja: IAsignacionCaja | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ asignacionCaja }) => {
      this.asignacionCaja = asignacionCaja;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
