import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ICaja, NewCaja } from '../caja.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICaja for edit and NewCajaFormGroupInput for create.
 */
type CajaFormGroupInput = ICaja | PartialWithRequiredKeyOf<NewCaja>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ICaja | NewCaja> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

type CajaFormRawValue = FormValueOf<ICaja>;

type NewCajaFormRawValue = FormValueOf<NewCaja>;

type CajaFormDefaults = Pick<NewCaja, 'id' | 'indDel' | 'fecCrea' | 'fecModif'>;

type CajaFormGroupContent = {
  id: FormControl<CajaFormRawValue['id'] | NewCaja['id']>;
  tipCaja: FormControl<CajaFormRawValue['tipCaja']>;
  descripcion: FormControl<CajaFormRawValue['descripcion']>;
  estado: FormControl<CajaFormRawValue['estado']>;
  version: FormControl<CajaFormRawValue['version']>;
  indDel: FormControl<CajaFormRawValue['indDel']>;
  fecCrea: FormControl<CajaFormRawValue['fecCrea']>;
  usuCrea: FormControl<CajaFormRawValue['usuCrea']>;
  ipCrea: FormControl<CajaFormRawValue['ipCrea']>;
  fecModif: FormControl<CajaFormRawValue['fecModif']>;
  usuModif: FormControl<CajaFormRawValue['usuModif']>;
  ipModif: FormControl<CajaFormRawValue['ipModif']>;
};

export type CajaFormGroup = FormGroup<CajaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CajaFormService {
  createCajaFormGroup(caja: CajaFormGroupInput = { id: null }): CajaFormGroup {
    const cajaRawValue = this.convertCajaToCajaRawValue({
      ...this.getFormDefaults(),
      ...caja,
    });
    return new FormGroup<CajaFormGroupContent>({
      id: new FormControl(
        { value: cajaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      tipCaja: new FormControl(cajaRawValue.tipCaja, {
        validators: [Validators.required],
      }),
      descripcion: new FormControl(cajaRawValue.descripcion, {
        validators: [Validators.required],
      }),
      estado: new FormControl(cajaRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(cajaRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(cajaRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(cajaRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(cajaRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(cajaRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(cajaRawValue.fecModif),
      usuModif: new FormControl(cajaRawValue.usuModif),
      ipModif: new FormControl(cajaRawValue.ipModif),
    });
  }

  getCaja(form: CajaFormGroup): ICaja | NewCaja {
    return this.convertCajaRawValueToCaja(form.getRawValue() as CajaFormRawValue | NewCajaFormRawValue);
  }

  resetForm(form: CajaFormGroup, caja: CajaFormGroupInput): void {
    const cajaRawValue = this.convertCajaToCajaRawValue({ ...this.getFormDefaults(), ...caja });
    form.reset(
      {
        ...cajaRawValue,
        id: { value: cajaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CajaFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertCajaRawValueToCaja(rawCaja: CajaFormRawValue | NewCajaFormRawValue): ICaja | NewCaja {
    return {
      ...rawCaja,
      fecCrea: dayjs(rawCaja.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawCaja.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertCajaToCajaRawValue(
    caja: ICaja | (Partial<NewCaja> & CajaFormDefaults)
  ): CajaFormRawValue | PartialWithRequiredKeyOf<NewCajaFormRawValue> {
    return {
      ...caja,
      fecCrea: caja.fecCrea ? caja.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: caja.fecModif ? caja.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
