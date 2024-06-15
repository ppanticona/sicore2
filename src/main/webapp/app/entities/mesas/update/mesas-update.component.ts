import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { MesasFormService, MesasFormGroup } from './mesas-form.service';
import { IMesas } from '../mesas.model';
import { MesasService } from '../service/mesas.service';
import { IOrden } from 'app/entities/orden/orden.model';
import { OrdenService } from 'app/entities/orden/service/orden.service';
import { ISede } from 'app/entities/sede/sede.model';
import { SedeService } from 'app/entities/sede/service/sede.service';
import { IEmpleados } from 'app/entities/empleados/empleados.model';
import { EmpleadosService } from 'app/entities/empleados/service/empleados.service';

@Component({
  selector: 'jhi-mesas-update',
  templateUrl: './mesas-update.component.html',
})
export class MesasUpdateComponent implements OnInit {
  isSaving = false;
  mesas: IMesas | null = null;

  ordensSharedCollection: IOrden[] = [];
  sedesSharedCollection: ISede[] = [];
  empleadosSharedCollection: IEmpleados[] = [];

  editForm: MesasFormGroup = this.mesasFormService.createMesasFormGroup();

  constructor(
    protected mesasService: MesasService,
    protected mesasFormService: MesasFormService,
    protected ordenService: OrdenService,
    protected sedeService: SedeService,
    protected empleadosService: EmpleadosService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareOrden = (o1: IOrden | null, o2: IOrden | null): boolean => this.ordenService.compareOrden(o1, o2);

  compareSede = (o1: ISede | null, o2: ISede | null): boolean => this.sedeService.compareSede(o1, o2);

  compareEmpleados = (o1: IEmpleados | null, o2: IEmpleados | null): boolean => this.empleadosService.compareEmpleados(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mesas }) => {
      this.mesas = mesas;
      if (mesas) {
        this.updateForm(mesas);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const mesas = this.mesasFormService.getMesas(this.editForm);
    if (mesas.id !== null) {
      this.subscribeToSaveResponse(this.mesasService.update(mesas));
    } else {
      this.subscribeToSaveResponse(this.mesasService.create(mesas));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMesas>>): void {
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

  protected updateForm(mesas: IMesas): void {
    this.mesas = mesas;
    this.mesasFormService.resetForm(this.editForm, mesas);

    this.ordensSharedCollection = this.ordenService.addOrdenToCollectionIfMissing<IOrden>(this.ordensSharedCollection, mesas.orden);
    this.sedesSharedCollection = this.sedeService.addSedeToCollectionIfMissing<ISede>(this.sedesSharedCollection, mesas.sede);
    this.empleadosSharedCollection = this.empleadosService.addEmpleadosToCollectionIfMissing<IEmpleados>(
      this.empleadosSharedCollection,
      mesas.empleado
    );
  }

  protected loadRelationshipsOptions(): void {
    this.ordenService
      .query()
      .pipe(map((res: HttpResponse<IOrden[]>) => res.body ?? []))
      .pipe(map((ordens: IOrden[]) => this.ordenService.addOrdenToCollectionIfMissing<IOrden>(ordens, this.mesas?.orden)))
      .subscribe((ordens: IOrden[]) => (this.ordensSharedCollection = ordens));

    this.sedeService
      .query()
      .pipe(map((res: HttpResponse<ISede[]>) => res.body ?? []))
      .pipe(map((sedes: ISede[]) => this.sedeService.addSedeToCollectionIfMissing<ISede>(sedes, this.mesas?.sede)))
      .subscribe((sedes: ISede[]) => (this.sedesSharedCollection = sedes));

    this.empleadosService
      .query()
      .pipe(map((res: HttpResponse<IEmpleados[]>) => res.body ?? []))
      .pipe(
        map((empleados: IEmpleados[]) =>
          this.empleadosService.addEmpleadosToCollectionIfMissing<IEmpleados>(empleados, this.mesas?.empleado)
        )
      )
      .subscribe((empleados: IEmpleados[]) => (this.empleadosSharedCollection = empleados));
  }
}
