import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SecuenciasFormService, SecuenciasFormGroup } from './secuencias-form.service';
import { ISecuencias } from '../secuencias.model';
import { SecuenciasService } from '../service/secuencias.service';

@Component({
  selector: 'jhi-secuencias-update',
  templateUrl: './secuencias-update.component.html',
})
export class SecuenciasUpdateComponent implements OnInit {
  isSaving = false;
  secuencias: ISecuencias | null = null;

  editForm: SecuenciasFormGroup = this.secuenciasFormService.createSecuenciasFormGroup();

  constructor(
    protected secuenciasService: SecuenciasService,
    protected secuenciasFormService: SecuenciasFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ secuencias }) => {
      this.secuencias = secuencias;
      if (secuencias) {
        this.updateForm(secuencias);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const secuencias = this.secuenciasFormService.getSecuencias(this.editForm);
    if (secuencias.id !== null) {
      this.subscribeToSaveResponse(this.secuenciasService.update(secuencias));
    } else {
      this.subscribeToSaveResponse(this.secuenciasService.create(secuencias));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISecuencias>>): void {
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

  protected updateForm(secuencias: ISecuencias): void {
    this.secuencias = secuencias;
    this.secuenciasFormService.resetForm(this.editForm, secuencias);
  }
}
