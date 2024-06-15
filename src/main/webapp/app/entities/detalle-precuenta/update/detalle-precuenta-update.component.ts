import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { DetallePrecuentaFormService, DetallePrecuentaFormGroup } from './detalle-precuenta-form.service';
import { IDetallePrecuenta } from '../detalle-precuenta.model';
import { DetallePrecuentaService } from '../service/detalle-precuenta.service';
import { IOrden } from 'app/entities/orden/orden.model';
import { OrdenService } from 'app/entities/orden/service/orden.service';
import { IAutorizacion } from 'app/entities/autorizacion/autorizacion.model';
import { AutorizacionService } from 'app/entities/autorizacion/service/autorizacion.service';
import { IRegVenta } from 'app/entities/reg-venta/reg-venta.model';
import { RegVentaService } from 'app/entities/reg-venta/service/reg-venta.service';
import { IPrecuenta } from 'app/entities/precuenta/precuenta.model';
import { PrecuentaService } from 'app/entities/precuenta/service/precuenta.service';
import { IDetalleOrden } from 'app/entities/detalle-orden/detalle-orden.model';
import { DetalleOrdenService } from 'app/entities/detalle-orden/service/detalle-orden.service';

@Component({
  selector: 'jhi-detalle-precuenta-update',
  templateUrl: './detalle-precuenta-update.component.html',
})
export class DetallePrecuentaUpdateComponent implements OnInit {
  isSaving = false;
  detallePrecuenta: IDetallePrecuenta | null = null;

  ordensSharedCollection: IOrden[] = [];
  autorizacionsSharedCollection: IAutorizacion[] = [];
  regVentasSharedCollection: IRegVenta[] = [];
  precuentasSharedCollection: IPrecuenta[] = [];
  detalleOrdensSharedCollection: IDetalleOrden[] = [];

  editForm: DetallePrecuentaFormGroup = this.detallePrecuentaFormService.createDetallePrecuentaFormGroup();

  constructor(
    protected detallePrecuentaService: DetallePrecuentaService,
    protected detallePrecuentaFormService: DetallePrecuentaFormService,
    protected ordenService: OrdenService,
    protected autorizacionService: AutorizacionService,
    protected regVentaService: RegVentaService,
    protected precuentaService: PrecuentaService,
    protected detalleOrdenService: DetalleOrdenService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareOrden = (o1: IOrden | null, o2: IOrden | null): boolean => this.ordenService.compareOrden(o1, o2);

  compareAutorizacion = (o1: IAutorizacion | null, o2: IAutorizacion | null): boolean =>
    this.autorizacionService.compareAutorizacion(o1, o2);

  compareRegVenta = (o1: IRegVenta | null, o2: IRegVenta | null): boolean => this.regVentaService.compareRegVenta(o1, o2);

  comparePrecuenta = (o1: IPrecuenta | null, o2: IPrecuenta | null): boolean => this.precuentaService.comparePrecuenta(o1, o2);

  compareDetalleOrden = (o1: IDetalleOrden | null, o2: IDetalleOrden | null): boolean =>
    this.detalleOrdenService.compareDetalleOrden(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detallePrecuenta }) => {
      this.detallePrecuenta = detallePrecuenta;
      if (detallePrecuenta) {
        this.updateForm(detallePrecuenta);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const detallePrecuenta = this.detallePrecuentaFormService.getDetallePrecuenta(this.editForm);
    if (detallePrecuenta.id !== null) {
      this.subscribeToSaveResponse(this.detallePrecuentaService.update(detallePrecuenta));
    } else {
      this.subscribeToSaveResponse(this.detallePrecuentaService.create(detallePrecuenta));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDetallePrecuenta>>): void {
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

  protected updateForm(detallePrecuenta: IDetallePrecuenta): void {
    this.detallePrecuenta = detallePrecuenta;
    this.detallePrecuentaFormService.resetForm(this.editForm, detallePrecuenta);

    this.ordensSharedCollection = this.ordenService.addOrdenToCollectionIfMissing<IOrden>(
      this.ordensSharedCollection,
      detallePrecuenta.orden
    );
    this.autorizacionsSharedCollection = this.autorizacionService.addAutorizacionToCollectionIfMissing<IAutorizacion>(
      this.autorizacionsSharedCollection,
      detallePrecuenta.autorizacion
    );
    this.regVentasSharedCollection = this.regVentaService.addRegVentaToCollectionIfMissing<IRegVenta>(
      this.regVentasSharedCollection,
      detallePrecuenta.regVenta
    );
    this.precuentasSharedCollection = this.precuentaService.addPrecuentaToCollectionIfMissing<IPrecuenta>(
      this.precuentasSharedCollection,
      detallePrecuenta.precuenta
    );
    this.detalleOrdensSharedCollection = this.detalleOrdenService.addDetalleOrdenToCollectionIfMissing<IDetalleOrden>(
      this.detalleOrdensSharedCollection,
      detallePrecuenta.detalleOrden
    );
  }

  protected loadRelationshipsOptions(): void {
    this.ordenService
      .query()
      .pipe(map((res: HttpResponse<IOrden[]>) => res.body ?? []))
      .pipe(map((ordens: IOrden[]) => this.ordenService.addOrdenToCollectionIfMissing<IOrden>(ordens, this.detallePrecuenta?.orden)))
      .subscribe((ordens: IOrden[]) => (this.ordensSharedCollection = ordens));

    this.autorizacionService
      .query()
      .pipe(map((res: HttpResponse<IAutorizacion[]>) => res.body ?? []))
      .pipe(
        map((autorizacions: IAutorizacion[]) =>
          this.autorizacionService.addAutorizacionToCollectionIfMissing<IAutorizacion>(autorizacions, this.detallePrecuenta?.autorizacion)
        )
      )
      .subscribe((autorizacions: IAutorizacion[]) => (this.autorizacionsSharedCollection = autorizacions));

    this.regVentaService
      .query()
      .pipe(map((res: HttpResponse<IRegVenta[]>) => res.body ?? []))
      .pipe(
        map((regVentas: IRegVenta[]) =>
          this.regVentaService.addRegVentaToCollectionIfMissing<IRegVenta>(regVentas, this.detallePrecuenta?.regVenta)
        )
      )
      .subscribe((regVentas: IRegVenta[]) => (this.regVentasSharedCollection = regVentas));

    this.precuentaService
      .query()
      .pipe(map((res: HttpResponse<IPrecuenta[]>) => res.body ?? []))
      .pipe(
        map((precuentas: IPrecuenta[]) =>
          this.precuentaService.addPrecuentaToCollectionIfMissing<IPrecuenta>(precuentas, this.detallePrecuenta?.precuenta)
        )
      )
      .subscribe((precuentas: IPrecuenta[]) => (this.precuentasSharedCollection = precuentas));

    this.detalleOrdenService
      .query()
      .pipe(map((res: HttpResponse<IDetalleOrden[]>) => res.body ?? []))
      .pipe(
        map((detalleOrdens: IDetalleOrden[]) =>
          this.detalleOrdenService.addDetalleOrdenToCollectionIfMissing<IDetalleOrden>(detalleOrdens, this.detallePrecuenta?.detalleOrden)
        )
      )
      .subscribe((detalleOrdens: IDetalleOrden[]) => (this.detalleOrdensSharedCollection = detalleOrdens));
  }
}
