import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { AutorizacionFormService, AutorizacionFormGroup } from './autorizacion-form.service';
import { IAutorizacion } from '../autorizacion.model';
import { AutorizacionService } from '../service/autorizacion.service';
import { IProducto } from 'app/entities/producto/producto.model';
import { ProductoService } from 'app/entities/producto/service/producto.service';

@Component({
  selector: 'jhi-autorizacion-update',
  templateUrl: './autorizacion-update.component.html',
})
export class AutorizacionUpdateComponent implements OnInit {
  isSaving = false;
  autorizacion: IAutorizacion | null = null;

  productosSharedCollection: IProducto[] = [];

  editForm: AutorizacionFormGroup = this.autorizacionFormService.createAutorizacionFormGroup();

  constructor(
    protected autorizacionService: AutorizacionService,
    protected autorizacionFormService: AutorizacionFormService,
    protected productoService: ProductoService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareProducto = (o1: IProducto | null, o2: IProducto | null): boolean => this.productoService.compareProducto(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autorizacion }) => {
      this.autorizacion = autorizacion;
      if (autorizacion) {
        this.updateForm(autorizacion);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const autorizacion = this.autorizacionFormService.getAutorizacion(this.editForm);
    if (autorizacion.id !== null) {
      this.subscribeToSaveResponse(this.autorizacionService.update(autorizacion));
    } else {
      this.subscribeToSaveResponse(this.autorizacionService.create(autorizacion));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAutorizacion>>): void {
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

  protected updateForm(autorizacion: IAutorizacion): void {
    this.autorizacion = autorizacion;
    this.autorizacionFormService.resetForm(this.editForm, autorizacion);

    this.productosSharedCollection = this.productoService.addProductoToCollectionIfMissing<IProducto>(
      this.productosSharedCollection,
      autorizacion.producto
    );
  }

  protected loadRelationshipsOptions(): void {
    this.productoService
      .query()
      .pipe(map((res: HttpResponse<IProducto[]>) => res.body ?? []))
      .pipe(
        map((productos: IProducto[]) =>
          this.productoService.addProductoToCollectionIfMissing<IProducto>(productos, this.autorizacion?.producto)
        )
      )
      .subscribe((productos: IProducto[]) => (this.productosSharedCollection = productos));
  }
}
