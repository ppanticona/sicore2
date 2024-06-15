import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { PromocionFormService, PromocionFormGroup } from './promocion-form.service';
import { IPromocion } from '../promocion.model';
import { PromocionService } from '../service/promocion.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { IAutorizacion } from 'app/entities/autorizacion/autorizacion.model';
import { AutorizacionService } from 'app/entities/autorizacion/service/autorizacion.service';

@Component({
  selector: 'jhi-promocion-update',
  templateUrl: './promocion-update.component.html',
})
export class PromocionUpdateComponent implements OnInit {
  isSaving = false;
  promocion: IPromocion | null = null;

  autorizacionsSharedCollection: IAutorizacion[] = [];

  editForm: PromocionFormGroup = this.promocionFormService.createPromocionFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected promocionService: PromocionService,
    protected promocionFormService: PromocionFormService,
    protected autorizacionService: AutorizacionService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareAutorizacion = (o1: IAutorizacion | null, o2: IAutorizacion | null): boolean =>
    this.autorizacionService.compareAutorizacion(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ promocion }) => {
      this.promocion = promocion;
      if (promocion) {
        this.updateForm(promocion);
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
    const promocion = this.promocionFormService.getPromocion(this.editForm);
    if (promocion.id !== null) {
      this.subscribeToSaveResponse(this.promocionService.update(promocion));
    } else {
      this.subscribeToSaveResponse(this.promocionService.create(promocion));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPromocion>>): void {
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

  protected updateForm(promocion: IPromocion): void {
    this.promocion = promocion;
    this.promocionFormService.resetForm(this.editForm, promocion);

    this.autorizacionsSharedCollection = this.autorizacionService.addAutorizacionToCollectionIfMissing<IAutorizacion>(
      this.autorizacionsSharedCollection,
      promocion.autorizacion
    );
  }

  protected loadRelationshipsOptions(): void {
    this.autorizacionService
      .query()
      .pipe(map((res: HttpResponse<IAutorizacion[]>) => res.body ?? []))
      .pipe(
        map((autorizacions: IAutorizacion[]) =>
          this.autorizacionService.addAutorizacionToCollectionIfMissing<IAutorizacion>(autorizacions, this.promocion?.autorizacion)
        )
      )
      .subscribe((autorizacions: IAutorizacion[]) => (this.autorizacionsSharedCollection = autorizacions));
  }
}
