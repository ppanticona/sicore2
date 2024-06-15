import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMovimientoCaja } from '../movimiento-caja.model';

@Component({
  selector: 'jhi-movimiento-caja-detail',
  templateUrl: './movimiento-caja-detail.component.html',
})
export class MovimientoCajaDetailComponent implements OnInit {
  movimientoCaja: IMovimientoCaja | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ movimientoCaja }) => {
      this.movimientoCaja = movimientoCaja;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
