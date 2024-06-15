import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IPrecuenta, NewPrecuenta } from '../precuenta.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPrecuenta for edit and NewPrecuentaFormGroupInput for create.
 */
type PrecuentaFormGroupInput = IPrecuenta | PartialWithRequiredKeyOf<NewPrecuenta>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IPrecuenta | NewPrecuenta> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

type PrecuentaFormRawValue = FormValueOf<IPrecuenta>;

type NewPrecuentaFormRawValue = FormValueOf<NewPrecuenta>;

type PrecuentaFormDefaults = Pick<NewPrecuenta, 'id' | 'indDel' | 'fecCrea' | 'fecModif'>;

type PrecuentaFormGroupContent = {
  id: FormControl<PrecuentaFormRawValue['id'] | NewPrecuenta['id']>;
  numPrecuenta: FormControl<PrecuentaFormRawValue['numPrecuenta']>;
  observacion: FormControl<PrecuentaFormRawValue['observacion']>;
  estado: FormControl<PrecuentaFormRawValue['estado']>;
  version: FormControl<PrecuentaFormRawValue['version']>;
  indDel: FormControl<PrecuentaFormRawValue['indDel']>;
  fecCrea: FormControl<PrecuentaFormRawValue['fecCrea']>;
  usuCrea: FormControl<PrecuentaFormRawValue['usuCrea']>;
  ipCrea: FormControl<PrecuentaFormRawValue['ipCrea']>;
  fecModif: FormControl<PrecuentaFormRawValue['fecModif']>;
  usuModif: FormControl<PrecuentaFormRawValue['usuModif']>;
  ipModif: FormControl<PrecuentaFormRawValue['ipModif']>;
  orden: FormControl<PrecuentaFormRawValue['orden']>;
};

export type PrecuentaFormGroup = FormGroup<PrecuentaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PrecuentaFormService {
  createPrecuentaFormGroup(precuenta: PrecuentaFormGroupInput = { id: null }): PrecuentaFormGroup {
    const precuentaRawValue = this.convertPrecuentaToPrecuentaRawValue({
      ...this.getFormDefaults(),
      ...precuenta,
    });
    return new FormGroup<PrecuentaFormGroupContent>({
      id: new FormControl(
        { value: precuentaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      numPrecuenta: new FormControl(precuentaRawValue.numPrecuenta, {
        validators: [Validators.required],
      }),
      observacion: new FormControl(precuentaRawValue.observacion),
      estado: new FormControl(precuentaRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(precuentaRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(precuentaRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(precuentaRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(precuentaRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(precuentaRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(precuentaRawValue.fecModif),
      usuModif: new FormControl(precuentaRawValue.usuModif),
      ipModif: new FormControl(precuentaRawValue.ipModif),
      orden: new FormControl(precuentaRawValue.orden),
    });
  }

  getPrecuenta(form: PrecuentaFormGroup): IPrecuenta | NewPrecuenta {
    return this.convertPrecuentaRawValueToPrecuenta(form.getRawValue() as PrecuentaFormRawValue | NewPrecuentaFormRawValue);
  }

  resetForm(form: PrecuentaFormGroup, precuenta: PrecuentaFormGroupInput): void {
    const precuentaRawValue = this.convertPrecuentaToPrecuentaRawValue({ ...this.getFormDefaults(), ...precuenta });
    form.reset(
      {
        ...precuentaRawValue,
        id: { value: precuentaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): PrecuentaFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertPrecuentaRawValueToPrecuenta(rawPrecuenta: PrecuentaFormRawValue | NewPrecuentaFormRawValue): IPrecuenta | NewPrecuenta {
    return {
      ...rawPrecuenta,
      fecCrea: dayjs(rawPrecuenta.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawPrecuenta.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertPrecuentaToPrecuentaRawValue(
    precuenta: IPrecuenta | (Partial<NewPrecuenta> & PrecuentaFormDefaults)
  ): PrecuentaFormRawValue | PartialWithRequiredKeyOf<NewPrecuentaFormRawValue> {
    return {
      ...precuenta,
      fecCrea: precuenta.fecCrea ? precuenta.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: precuenta.fecModif ? precuenta.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
