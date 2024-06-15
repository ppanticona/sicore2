import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { DetalleOrdenFormService, DetalleOrdenFormGroup } from './detalle-orden-form.service';
import { IDetalleOrden } from '../detalle-orden.model';
import { DetalleOrdenService } from '../service/detalle-orden.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { IOrden } from 'app/entities/orden/orden.model';
import { OrdenService } from 'app/entities/orden/service/orden.service';
import { IPromocion } from 'app/entities/promocion/promocion.model';
import { PromocionService } from 'app/entities/promocion/service/promocion.service';
import { IAutorizacion } from 'app/entities/autorizacion/autorizacion.model';
import { AutorizacionService } from 'app/entities/autorizacion/service/autorizacion.service';
import { IProducto } from 'app/entities/producto/producto.model';
import { ProductoService } from 'app/entities/producto/service/producto.service';

@Component({
  selector: 'jhi-detalle-orden-update',
  templateUrl: './detalle-orden-update.component.html',
})
export class DetalleOrdenUpdateComponent implements OnInit {
  isSaving = false;
  detalleOrden: IDetalleOrden | null = null;

  ordensSharedCollection: IOrden[] = [];
  promocionsSharedCollection: IPromocion[] = [];
  autorizacionsSharedCollection: IAutorizacion[] = [];
  productosSharedCollection: IProducto[] = [];

  editForm: DetalleOrdenFormGroup = this.detalleOrdenFormService.createDetalleOrdenFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected detalleOrdenService: DetalleOrdenService,
    protected detalleOrdenFormService: DetalleOrdenFormService,
    protected ordenService: OrdenService,
    protected promocionService: PromocionService,
    protected autorizacionService: AutorizacionService,
    protected productoService: ProductoService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareOrden = (o1: IOrden | null, o2: IOrden | null): boolean => this.ordenService.compareOrden(o1, o2);

  comparePromocion = (o1: IPromocion | null, o2: IPromocion | null): boolean => this.promocionService.comparePromocion(o1, o2);

  compareAutorizacion = (o1: IAutorizacion | null, o2: IAutorizacion | null): boolean =>
    this.autorizacionService.compareAutorizacion(o1, o2);

  compareProducto = (o1: IProducto | null, o2: IProducto | null): boolean => this.productoService.compareProducto(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detalleOrden }) => {
      this.detalleOrden = detalleOrden;
      if (detalleOrden) {
        this.updateForm(detalleOrden);
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
    const detalleOrden = this.detalleOrdenFormService.getDetalleOrden(this.editForm);
    if (detalleOrden.id !== null) {
      this.subscribeToSaveResponse(this.detalleOrdenService.update(detalleOrden));
    } else {
      this.subscribeToSaveResponse(this.detalleOrdenService.create(detalleOrden));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDetalleOrden>>): void {
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

  protected updateForm(detalleOrden: IDetalleOrden): void {
    this.detalleOrden = detalleOrden;
    this.detalleOrdenFormService.resetForm(this.editForm, detalleOrden);

    this.ordensSharedCollection = this.ordenService.addOrdenToCollectionIfMissing<IOrden>(this.ordensSharedCollection, detalleOrden.orden);
    this.promocionsSharedCollection = this.promocionService.addPromocionToCollectionIfMissing<IPromocion>(
      this.promocionsSharedCollection,
      detalleOrden.promocion
    );
    this.autorizacionsSharedCollection = this.autorizacionService.addAutorizacionToCollectionIfMissing<IAutorizacion>(
      this.autorizacionsSharedCollection,
      detalleOrden.autorizacion
    );
    this.productosSharedCollection = this.productoService.addProductoToCollectionIfMissing<IProducto>(
      this.productosSharedCollection,
      detalleOrden.producto
    );
  }

  protected loadRelationshipsOptions(): void {
    this.ordenService
      .query()
      .pipe(map((res: HttpResponse<IOrden[]>) => res.body ?? []))
      .pipe(map((ordens: IOrden[]) => this.ordenService.addOrdenToCollectionIfMissing<IOrden>(ordens, this.detalleOrden?.orden)))
      .subscribe((ordens: IOrden[]) => (this.ordensSharedCollection = ordens));

    this.promocionService
      .query()
      .pipe(map((res: HttpResponse<IPromocion[]>) => res.body ?? []))
      .pipe(
        map((promocions: IPromocion[]) =>
          this.promocionService.addPromocionToCollectionIfMissing<IPromocion>(promocions, this.detalleOrden?.promocion)
        )
      )
      .subscribe((promocions: IPromocion[]) => (this.promocionsSharedCollection = promocions));

    this.autorizacionService
      .query()
      .pipe(map((res: HttpResponse<IAutorizacion[]>) => res.body ?? []))
      .pipe(
        map((autorizacions: IAutorizacion[]) =>
          this.autorizacionService.addAutorizacionToCollectionIfMissing<IAutorizacion>(autorizacions, this.detalleOrden?.autorizacion)
        )
      )
      .subscribe((autorizacions: IAutorizacion[]) => (this.autorizacionsSharedCollection = autorizacions));

    this.productoService
      .query()
      .pipe(map((res: HttpResponse<IProducto[]>) => res.body ?? []))
      .pipe(
        map((productos: IProducto[]) =>
          this.productoService.addProductoToCollectionIfMissing<IProducto>(productos, this.detalleOrden?.producto)
        )
      )
      .subscribe((productos: IProducto[]) => (this.productosSharedCollection = productos));
  }
}
