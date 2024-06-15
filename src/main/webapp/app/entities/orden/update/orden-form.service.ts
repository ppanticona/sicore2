import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IOrden, NewOrden } from '../orden.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOrden for edit and NewOrdenFormGroupInput for create.
 */
type OrdenFormGroupInput = IOrden | PartialWithRequiredKeyOf<NewOrden>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IOrden | NewOrden> = Omit<T, 'fecIngreso' | 'fecSalida' | 'fecCrea' | 'fecModif'> & {
  fecIngreso?: string | null;
  fecSalida?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type OrdenFormRawValue = FormValueOf<IOrden>;

type NewOrdenFormRawValue = FormValueOf<NewOrden>;

type OrdenFormDefaults = Pick<NewOrden, 'id' | 'fecIngreso' | 'fecSalida' | 'indDel' | 'fecCrea' | 'fecModif'>;

type OrdenFormGroupContent = {
  id: FormControl<OrdenFormRawValue['id'] | NewOrden['id']>;
  numOrden: FormControl<OrdenFormRawValue['numOrden']>;
  observacion: FormControl<OrdenFormRawValue['observacion']>;
  fecIngreso: FormControl<OrdenFormRawValue['fecIngreso']>;
  fecSalida: FormControl<OrdenFormRawValue['fecSalida']>;
  codCanal: FormControl<OrdenFormRawValue['codCanal']>;
  tipOrden: FormControl<OrdenFormRawValue['tipOrden']>;
  estado: FormControl<OrdenFormRawValue['estado']>;
  version: FormControl<OrdenFormRawValue['version']>;
  indDel: FormControl<OrdenFormRawValue['indDel']>;
  fecCrea: FormControl<OrdenFormRawValue['fecCrea']>;
  usuCrea: FormControl<OrdenFormRawValue['usuCrea']>;
  ipCrea: FormControl<OrdenFormRawValue['ipCrea']>;
  fecModif: FormControl<OrdenFormRawValue['fecModif']>;
  usuModif: FormControl<OrdenFormRawValue['usuModif']>;
  ipModif: FormControl<OrdenFormRawValue['ipModif']>;
};

export type OrdenFormGroup = FormGroup<OrdenFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OrdenFormService {
  createOrdenFormGroup(orden: OrdenFormGroupInput = { id: null }): OrdenFormGroup {
    const ordenRawValue = this.convertOrdenToOrdenRawValue({
      ...this.getFormDefaults(),
      ...orden,
    });
    return new FormGroup<OrdenFormGroupContent>({
      id: new FormControl(
        { value: ordenRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      numOrden: new FormControl(ordenRawValue.numOrden, {
        validators: [Validators.required],
      }),
      observacion: new FormControl(ordenRawValue.observacion),
      fecIngreso: new FormControl(ordenRawValue.fecIngreso),
      fecSalida: new FormControl(ordenRawValue.fecSalida),
      codCanal: new FormControl(ordenRawValue.codCanal, {
        validators: [Validators.required],
      }),
      tipOrden: new FormControl(ordenRawValue.tipOrden, {
        validators: [Validators.required],
      }),
      estado: new FormControl(ordenRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(ordenRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(ordenRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(ordenRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(ordenRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(ordenRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(ordenRawValue.fecModif),
      usuModif: new FormControl(ordenRawValue.usuModif),
      ipModif: new FormControl(ordenRawValue.ipModif),
    });
  }

  getOrden(form: OrdenFormGroup): IOrden | NewOrden {
    return this.convertOrdenRawValueToOrden(form.getRawValue() as OrdenFormRawValue | NewOrdenFormRawValue);
  }

  resetForm(form: OrdenFormGroup, orden: OrdenFormGroupInput): void {
    const ordenRawValue = this.convertOrdenToOrdenRawValue({ ...this.getFormDefaults(), ...orden });
    form.reset(
      {
        ...ordenRawValue,
        id: { value: ordenRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): OrdenFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecIngreso: currentTime,
      fecSalida: currentTime,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertOrdenRawValueToOrden(rawOrden: OrdenFormRawValue | NewOrdenFormRawValue): IOrden | NewOrden {
    return {
      ...rawOrden,
      fecIngreso: dayjs(rawOrden.fecIngreso, DATE_TIME_FORMAT),
      fecSalida: dayjs(rawOrden.fecSalida, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawOrden.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawOrden.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertOrdenToOrdenRawValue(
    orden: IOrden | (Partial<NewOrden> & OrdenFormDefaults)
  ): OrdenFormRawValue | PartialWithRequiredKeyOf<NewOrdenFormRawValue> {
    return {
      ...orden,
      fecIngreso: orden.fecIngreso ? orden.fecIngreso.format(DATE_TIME_FORMAT) : undefined,
      fecSalida: orden.fecSalida ? orden.fecSalida.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: orden.fecCrea ? orden.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: orden.fecModif ? orden.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
