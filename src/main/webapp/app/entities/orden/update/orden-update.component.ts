import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { OrdenFormService, OrdenFormGroup } from './orden-form.service';
import { IOrden } from '../orden.model';
import { OrdenService } from '../service/orden.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-orden-update',
  templateUrl: './orden-update.component.html',
})
export class OrdenUpdateComponent implements OnInit {
  isSaving = false;
  orden: IOrden | null = null;

  editForm: OrdenFormGroup = this.ordenFormService.createOrdenFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected ordenService: OrdenService,
    protected ordenFormService: OrdenFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orden }) => {
      this.orden = orden;
      if (orden) {
        this.updateForm(orden);
      }
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
    const orden = this.ordenFormService.getOrden(this.editForm);
    if (orden.id !== null) {
      this.subscribeToSaveResponse(this.ordenService.update(orden));
    } else {
      this.subscribeToSaveResponse(this.ordenService.create(orden));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrden>>): void {
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

  protected updateForm(orden: IOrden): void {
    this.orden = orden;
    this.ordenFormService.resetForm(this.editForm, orden);
  }
}
