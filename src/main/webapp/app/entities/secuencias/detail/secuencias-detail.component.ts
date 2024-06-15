import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISecuencias } from '../secuencias.model';

@Component({
  selector: 'jhi-secuencias-detail',
  templateUrl: './secuencias-detail.component.html',
})
export class SecuenciasDetailComponent implements OnInit {
  secuencias: ISecuencias | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ secuencias }) => {
      this.secuencias = secuencias;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
