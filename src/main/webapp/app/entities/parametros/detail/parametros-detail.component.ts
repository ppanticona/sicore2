import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IParametros } from '../parametros.model';

@Component({
  selector: 'jhi-parametros-detail',
  templateUrl: './parametros-detail.component.html',
})
export class ParametrosDetailComponent implements OnInit {
  parametros: IParametros | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ parametros }) => {
      this.parametros = parametros;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
