import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { RegVentaFormService, RegVentaFormGroup } from './reg-venta-form.service';
import { IRegVenta } from '../reg-venta.model';
import { RegVentaService } from '../service/reg-venta.service';
import { IOrden } from 'app/entities/orden/orden.model';
import { OrdenService } from 'app/entities/orden/service/orden.service';

@Component({
  selector: 'jhi-reg-venta-update',
  templateUrl: './reg-venta-update.component.html',
})
export class RegVentaUpdateComponent implements OnInit {
  isSaving = false;
  regVenta: IRegVenta | null = null;

  ordensSharedCollection: IOrden[] = [];

  editForm: RegVentaFormGroup = this.regVentaFormService.createRegVentaFormGroup();

  constructor(
    protected regVentaService: RegVentaService,
    protected regVentaFormService: RegVentaFormService,
    protected ordenService: OrdenService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareOrden = (o1: IOrden | null, o2: IOrden | null): boolean => this.ordenService.compareOrden(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ regVenta }) => {
      this.regVenta = regVenta;
      if (regVenta) {
        this.updateForm(regVenta);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const regVenta = this.regVentaFormService.getRegVenta(this.editForm);
    if (regVenta.id !== null) {
      this.subscribeToSaveResponse(this.regVentaService.update(regVenta));
    } else {
      this.subscribeToSaveResponse(this.regVentaService.create(regVenta));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRegVenta>>): void {
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

  protected updateForm(regVenta: IRegVenta): void {
    this.regVenta = regVenta;
    this.regVentaFormService.resetForm(this.editForm, regVenta);

    this.ordensSharedCollection = this.ordenService.addOrdenToCollectionIfMissing<IOrden>(this.ordensSharedCollection, regVenta.orden);
  }

  protected loadRelationshipsOptions(): void {
    this.ordenService
      .query()
      .pipe(map((res: HttpResponse<IOrden[]>) => res.body ?? []))
      .pipe(map((ordens: IOrden[]) => this.ordenService.addOrdenToCollectionIfMissing<IOrden>(ordens, this.regVenta?.orden)))
      .subscribe((ordens: IOrden[]) => (this.ordensSharedCollection = ordens));
  }
}
