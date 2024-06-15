import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { EmpleadosFormService, EmpleadosFormGroup } from './empleados-form.service';
import { IEmpleados } from '../empleados.model';
import { EmpleadosService } from '../service/empleados.service';

@Component({
  selector: 'jhi-empleados-update',
  templateUrl: './empleados-update.component.html',
})
export class EmpleadosUpdateComponent implements OnInit {
  isSaving = false;
  empleados: IEmpleados | null = null;

  editForm: EmpleadosFormGroup = this.empleadosFormService.createEmpleadosFormGroup();

  constructor(
    protected empleadosService: EmpleadosService,
    protected empleadosFormService: EmpleadosFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ empleados }) => {
      this.empleados = empleados;
      if (empleados) {
        this.updateForm(empleados);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const empleados = this.empleadosFormService.getEmpleados(this.editForm);
    if (empleados.id !== null) {
      this.subscribeToSaveResponse(this.empleadosService.update(empleados));
    } else {
      this.subscribeToSaveResponse(this.empleadosService.create(empleados));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmpleados>>): void {
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

  protected updateForm(empleados: IEmpleados): void {
    this.empleados = empleados;
    this.empleadosFormService.resetForm(this.editForm, empleados);
  }
}
