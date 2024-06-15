import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRegCompras } from '../reg-compras.model';

@Component({
  selector: 'jhi-reg-compras-detail',
  templateUrl: './reg-compras-detail.component.html',
})
export class RegComprasDetailComponent implements OnInit {
  regCompras: IRegCompras | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ regCompras }) => {
      this.regCompras = regCompras;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
