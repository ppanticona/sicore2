import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRegVenta } from '../reg-venta.model';

@Component({
  selector: 'jhi-reg-venta-detail',
  templateUrl: './reg-venta-detail.component.html',
})
export class RegVentaDetailComponent implements OnInit {
  regVenta: IRegVenta | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ regVenta }) => {
      this.regVenta = regVenta;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
