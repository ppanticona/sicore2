import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ParametrosFormService, ParametrosFormGroup } from './parametros-form.service';
import { IParametros } from '../parametros.model';
import { ParametrosService } from '../service/parametros.service';

@Component({
  selector: 'jhi-parametros-update',
  templateUrl: './parametros-update.component.html',
})
export class ParametrosUpdateComponent implements OnInit {
  isSaving = false;
  parametros: IParametros | null = null;

  editForm: ParametrosFormGroup = this.parametrosFormService.createParametrosFormGroup();

  constructor(
    protected parametrosService: ParametrosService,
    protected parametrosFormService: ParametrosFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ parametros }) => {
      this.parametros = parametros;
      if (parametros) {
        this.updateForm(parametros);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const parametros = this.parametrosFormService.getParametros(this.editForm);
    if (parametros.id !== null) {
      this.subscribeToSaveResponse(this.parametrosService.update(parametros));
    } else {
      this.subscribeToSaveResponse(this.parametrosService.create(parametros));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IParametros>>): void {
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

  protected updateForm(parametros: IParametros): void {
    this.parametros = parametros;
    this.parametrosFormService.resetForm(this.editForm, parametros);
  }
}
