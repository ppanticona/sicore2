import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAsistencia } from '../asistencia.model';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-asistencia-detail',
  templateUrl: './asistencia-detail.component.html',
})
export class AsistenciaDetailComponent implements OnInit {
  asistencia: IAsistencia | null = null;

  constructor(protected dataUtils: DataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ asistencia }) => {
      this.asistencia = asistencia;
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  previousState(): void {
    window.history.back();
  }
}
