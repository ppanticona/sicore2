import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { SedeFormService, SedeFormGroup } from './sede-form.service';
import { ISede } from '../sede.model';
import { SedeService } from '../service/sede.service';

@Component({
  selector: 'jhi-sede-update',
  templateUrl: './sede-update.component.html',
})
export class SedeUpdateComponent implements OnInit {
  isSaving = false;
  sede: ISede | null = null;

  editForm: SedeFormGroup = this.sedeFormService.createSedeFormGroup();

  constructor(protected sedeService: SedeService, protected sedeFormService: SedeFormService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sede }) => {
      this.sede = sede;
      if (sede) {
        this.updateForm(sede);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sede = this.sedeFormService.getSede(this.editForm);
    if (sede.id !== null) {
      this.subscribeToSaveResponse(this.sedeService.update(sede));
    } else {
      this.subscribeToSaveResponse(this.sedeService.create(sede));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISede>>): void {
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

  protected updateForm(sede: ISede): void {
    this.sede = sede;
    this.sedeFormService.resetForm(this.editForm, sede);
  }
}
