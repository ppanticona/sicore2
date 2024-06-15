import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { MovimientoCajaFormService, MovimientoCajaFormGroup } from './movimiento-caja-form.service';
import { IMovimientoCaja } from '../movimiento-caja.model';
import { MovimientoCajaService } from '../service/movimiento-caja.service';
import { IAsignacionCaja } from 'app/entities/asignacion-caja/asignacion-caja.model';
import { AsignacionCajaService } from 'app/entities/asignacion-caja/service/asignacion-caja.service';
import { IAutorizacion } from 'app/entities/autorizacion/autorizacion.model';
import { AutorizacionService } from 'app/entities/autorizacion/service/autorizacion.service';

@Component({
  selector: 'jhi-movimiento-caja-update',
  templateUrl: './movimiento-caja-update.component.html',
})
export class MovimientoCajaUpdateComponent implements OnInit {
  isSaving = false;
  movimientoCaja: IMovimientoCaja | null = null;

  asignacionCajasSharedCollection: IAsignacionCaja[] = [];
  autorizacionsSharedCollection: IAutorizacion[] = [];

  editForm: MovimientoCajaFormGroup = this.movimientoCajaFormService.createMovimientoCajaFormGroup();

  constructor(
    protected movimientoCajaService: MovimientoCajaService,
    protected movimientoCajaFormService: MovimientoCajaFormService,
    protected asignacionCajaService: AsignacionCajaService,
    protected autorizacionService: AutorizacionService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareAsignacionCaja = (o1: IAsignacionCaja | null, o2: IAsignacionCaja | null): boolean =>
    this.asignacionCajaService.compareAsignacionCaja(o1, o2);

  compareAutorizacion = (o1: IAutorizacion | null, o2: IAutorizacion | null): boolean =>
    this.autorizacionService.compareAutorizacion(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ movimientoCaja }) => {
      this.movimientoCaja = movimientoCaja;
      if (movimientoCaja) {
        this.updateForm(movimientoCaja);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const movimientoCaja = this.movimientoCajaFormService.getMovimientoCaja(this.editForm);
    if (movimientoCaja.id !== null) {
      this.subscribeToSaveResponse(this.movimientoCajaService.update(movimientoCaja));
    } else {
      this.subscribeToSaveResponse(this.movimientoCajaService.create(movimientoCaja));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMovimientoCaja>>): void {
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

  protected updateForm(movimientoCaja: IMovimientoCaja): void {
    this.movimientoCaja = movimientoCaja;
    this.movimientoCajaFormService.resetForm(this.editForm, movimientoCaja);

    this.asignacionCajasSharedCollection = this.asignacionCajaService.addAsignacionCajaToCollectionIfMissing<IAsignacionCaja>(
      this.asignacionCajasSharedCollection,
      movimientoCaja.asignacionCaja
    );
    this.autorizacionsSharedCollection = this.autorizacionService.addAutorizacionToCollectionIfMissing<IAutorizacion>(
      this.autorizacionsSharedCollection,
      movimientoCaja.autorizacion
    );
  }

  protected loadRelationshipsOptions(): void {
    this.asignacionCajaService
      .query()
      .pipe(map((res: HttpResponse<IAsignacionCaja[]>) => res.body ?? []))
      .pipe(
        map((asignacionCajas: IAsignacionCaja[]) =>
          this.asignacionCajaService.addAsignacionCajaToCollectionIfMissing<IAsignacionCaja>(
            asignacionCajas,
            this.movimientoCaja?.asignacionCaja
          )
        )
      )
      .subscribe((asignacionCajas: IAsignacionCaja[]) => (this.asignacionCajasSharedCollection = asignacionCajas));

    this.autorizacionService
      .query()
      .pipe(map((res: HttpResponse<IAutorizacion[]>) => res.body ?? []))
      .pipe(
        map((autorizacions: IAutorizacion[]) =>
          this.autorizacionService.addAutorizacionToCollectionIfMissing<IAutorizacion>(autorizacions, this.movimientoCaja?.autorizacion)
        )
      )
      .subscribe((autorizacions: IAutorizacion[]) => (this.autorizacionsSharedCollection = autorizacions));
  }
}
