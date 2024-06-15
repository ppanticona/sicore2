import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDetallePrecuenta } from '../detalle-precuenta.model';

@Component({
  selector: 'jhi-detalle-precuenta-detail',
  templateUrl: './detalle-precuenta-detail.component.html',
})
export class DetallePrecuentaDetailComponent implements OnInit {
  detallePrecuenta: IDetallePrecuenta | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detallePrecuenta }) => {
      this.detallePrecuenta = detallePrecuenta;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
