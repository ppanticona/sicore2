import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { PrecuentaFormService, PrecuentaFormGroup } from './precuenta-form.service';
import { IPrecuenta } from '../precuenta.model';
import { PrecuentaService } from '../service/precuenta.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { IOrden } from 'app/entities/orden/orden.model';
import { OrdenService } from 'app/entities/orden/service/orden.service';

@Component({
  selector: 'jhi-precuenta-update',
  templateUrl: './precuenta-update.component.html',
})
export class PrecuentaUpdateComponent implements OnInit {
  isSaving = false;
  precuenta: IPrecuenta | null = null;

  ordensSharedCollection: IOrden[] = [];

  editForm: PrecuentaFormGroup = this.precuentaFormService.createPrecuentaFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected precuentaService: PrecuentaService,
    protected precuentaFormService: PrecuentaFormService,
    protected ordenService: OrdenService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareOrden = (o1: IOrden | null, o2: IOrden | null): boolean => this.ordenService.compareOrden(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ precuenta }) => {
      this.precuenta = precuenta;
      if (precuenta) {
        this.updateForm(precuenta);
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
    const precuenta = this.precuentaFormService.getPrecuenta(this.editForm);
    if (precuenta.id !== null) {
      this.subscribeToSaveResponse(this.precuentaService.update(precuenta));
    } else {
      this.subscribeToSaveResponse(this.precuentaService.create(precuenta));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrecuenta>>): void {
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

  protected updateForm(precuenta: IPrecuenta): void {
    this.precuenta = precuenta;
    this.precuentaFormService.resetForm(this.editForm, precuenta);

    this.ordensSharedCollection = this.ordenService.addOrdenToCollectionIfMissing<IOrden>(this.ordensSharedCollection, precuenta.orden);
  }

  protected loadRelationshipsOptions(): void {
    this.ordenService
      .query()
      .pipe(map((res: HttpResponse<IOrden[]>) => res.body ?? []))
      .pipe(map((ordens: IOrden[]) => this.ordenService.addOrdenToCollectionIfMissing<IOrden>(ordens, this.precuenta?.orden)))
      .subscribe((ordens: IOrden[]) => (this.ordensSharedCollection = ordens));
  }
}
