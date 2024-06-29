import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { AsignacionCajaFormService, AsignacionCajaFormGroup } from './asignacion-caja-form.service';
import { IAsignacionCaja } from '../asignacion-caja.model';
import { AsignacionCajaService } from '../service/asignacion-caja.service';
import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';
import { ICaja } from 'app/entities/caja/caja.model';
import { CajaService } from 'app/entities/caja/service/caja.service';

@Component({
  selector: 'jhi-asignacion-caja-update',
  templateUrl: './asignacion-caja-update.component.html',
})
export class AsignacionCajaUpdateComponent implements OnInit {
  isSaving = false;
  asignacionCaja: IAsignacionCaja | null = null;

  usersSharedCollection: IUser[] = [];
  cajasSharedCollection: ICaja[] = [];

  editForm: AsignacionCajaFormGroup = this.asignacionCajaFormService.createAsignacionCajaFormGroup();

  constructor(
    protected asignacionCajaService: AsignacionCajaService,
    protected asignacionCajaFormService: AsignacionCajaFormService,
    protected userService: UserService,
    protected cajaService: CajaService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareUser = (o1: IUser | null, o2: IUser | null): boolean => this.userService.compareUser(o1, o2);

  compareCaja = (o1: ICaja | null, o2: ICaja | null): boolean => this.cajaService.compareCaja(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ asignacionCaja }) => {
      this.asignacionCaja = asignacionCaja;
      if (asignacionCaja) {
        this.updateForm(asignacionCaja);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const asignacionCaja = this.asignacionCajaFormService.getAsignacionCaja(this.editForm);
    if (asignacionCaja.id !== null) {
      this.subscribeToSaveResponse(this.asignacionCajaService.update(asignacionCaja));
    } else {
      this.subscribeToSaveResponse(this.asignacionCajaService.create(asignacionCaja));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAsignacionCaja>>): void {
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

  protected updateForm(asignacionCaja: IAsignacionCaja): void {
    this.asignacionCaja = asignacionCaja;
    this.asignacionCajaFormService.resetForm(this.editForm, asignacionCaja);

    this.usersSharedCollection = this.userService.addUserToCollectionIfMissing<IUser>(this.usersSharedCollection, asignacionCaja.user);
    this.cajasSharedCollection = this.cajaService.addCajaToCollectionIfMissing<ICaja>(this.cajasSharedCollection, asignacionCaja.caja);
  }

  protected loadRelationshipsOptions(): void {
    this.userService
      .query()
      .pipe(map((res: HttpResponse<IUser[]>) => res.body ?? []))
      .pipe(map((users: IUser[]) => this.userService.addUserToCollectionIfMissing<IUser>(users, this.asignacionCaja?.user)))
      .subscribe((users: IUser[]) => (this.usersSharedCollection = users));

    this.cajaService
      .query()
      .pipe(map((res: HttpResponse<ICaja[]>) => res.body ?? []))
      .pipe(map((cajas: ICaja[]) => this.cajaService.addCajaToCollectionIfMissing<ICaja>(cajas, this.asignacionCaja?.caja)))
      .subscribe((cajas: ICaja[]) => (this.cajasSharedCollection = cajas));
  }
}
