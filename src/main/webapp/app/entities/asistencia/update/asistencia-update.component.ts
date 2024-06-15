import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { AsistenciaFormService, AsistenciaFormGroup } from './asistencia-form.service';
import { IAsistencia } from '../asistencia.model';
import { AsistenciaService } from '../service/asistencia.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { IEmpleados } from 'app/entities/empleados/empleados.model';
import { EmpleadosService } from 'app/entities/empleados/service/empleados.service';

@Component({
  selector: 'jhi-asistencia-update',
  templateUrl: './asistencia-update.component.html',
})
export class AsistenciaUpdateComponent implements OnInit {
  isSaving = false;
  asistencia: IAsistencia | null = null;

  empleadosSharedCollection: IEmpleados[] = [];

  editForm: AsistenciaFormGroup = this.asistenciaFormService.createAsistenciaFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected asistenciaService: AsistenciaService,
    protected asistenciaFormService: AsistenciaFormService,
    protected empleadosService: EmpleadosService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareEmpleados = (o1: IEmpleados | null, o2: IEmpleados | null): boolean => this.empleadosService.compareEmpleados(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ asistencia }) => {
      this.asistencia = asistencia;
      if (asistencia) {
        this.updateForm(asistencia);
      }

      this.loadRelationshipsOptions();
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(new EventWithContent<AlertError>('sicore2App.error', { ...err, key: 'error.file.' + err.key })),
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const asistencia = this.asistenciaFormService.getAsistencia(this.editForm);
    if (asistencia.id !== null) {
      this.subscribeToSaveResponse(this.asistenciaService.update(asistencia));
    } else {
      this.subscribeToSaveResponse(this.asistenciaService.create(asistencia));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAsistencia>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(asistencia: IAsistencia): void {
    this.asistencia = asistencia;
    this.asistenciaFormService.resetForm(this.editForm, asistencia);

    this.empleadosSharedCollection = this.empleadosService.addEmpleadosToCollectionIfMissing<IEmpleados>(
      this.empleadosSharedCollection,
      asistencia.userId
    );
  }

  protected loadRelationshipsOptions(): void {
    this.empleadosService
      .query()
      .pipe(map((res: HttpResponse<IEmpleados[]>) => res.body ?? []))
      .pipe(
        map((empleados: IEmpleados[]) =>
          this.empleadosService.addEmpleadosToCollectionIfMissing<IEmpleados>(empleados, this.asistencia?.userId)
        )
      )
      .subscribe((empleados: IEmpleados[]) => (this.empleadosSharedCollection = empleados));
  }
}
