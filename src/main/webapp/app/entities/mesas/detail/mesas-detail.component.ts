import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMesas } from '../mesas.model';

@Component({
  selector: 'jhi-mesas-detail',
  templateUrl: './mesas-detail.component.html',
})
export class MesasDetailComponent implements OnInit {
  mesas: IMesas | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mesas }) => {
      this.mesas = mesas;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
