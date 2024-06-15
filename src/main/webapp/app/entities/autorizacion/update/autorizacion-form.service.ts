import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAutorizacion, NewAutorizacion } from '../autorizacion.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAutorizacion for edit and NewAutorizacionFormGroupInput for create.
 */
type AutorizacionFormGroupInput = IAutorizacion | PartialWithRequiredKeyOf<NewAutorizacion>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IAutorizacion | NewAutorizacion> = Omit<T, 'fecIniVig' | 'fecFinVig' | 'fecCrea' | 'fecModif'> & {
  fecIniVig?: string | null;
  fecFinVig?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type AutorizacionFormRawValue = FormValueOf<IAutorizacion>;

type NewAutorizacionFormRawValue = FormValueOf<NewAutorizacion>;

type AutorizacionFormDefaults = Pick<NewAutorizacion, 'id' | 'fecIniVig' | 'fecFinVig' | 'indDel' | 'fecCrea' | 'fecModif'>;

type AutorizacionFormGroupContent = {
  id: FormControl<AutorizacionFormRawValue['id'] | NewAutorizacion['id']>;
  tipAutorizacion: FormControl<AutorizacionFormRawValue['tipAutorizacion']>;
  subTipAutorizacion: FormControl<AutorizacionFormRawValue['subTipAutorizacion']>;
  concepto: FormControl<AutorizacionFormRawValue['concepto']>;
  comentario: FormControl<AutorizacionFormRawValue['comentario']>;
  monto: FormControl<AutorizacionFormRawValue['monto']>;
  moneda: FormControl<AutorizacionFormRawValue['moneda']>;
  token: FormControl<AutorizacionFormRawValue['token']>;
  fecIniVig: FormControl<AutorizacionFormRawValue['fecIniVig']>;
  fecFinVig: FormControl<AutorizacionFormRawValue['fecFinVig']>;
  estado: FormControl<AutorizacionFormRawValue['estado']>;
  version: FormControl<AutorizacionFormRawValue['version']>;
  indDel: FormControl<AutorizacionFormRawValue['indDel']>;
  fecCrea: FormControl<AutorizacionFormRawValue['fecCrea']>;
  usuCrea: FormControl<AutorizacionFormRawValue['usuCrea']>;
  ipCrea: FormControl<AutorizacionFormRawValue['ipCrea']>;
  fecModif: FormControl<AutorizacionFormRawValue['fecModif']>;
  usuModif: FormControl<AutorizacionFormRawValue['usuModif']>;
  ipModif: FormControl<AutorizacionFormRawValue['ipModif']>;
  producto: FormControl<AutorizacionFormRawValue['producto']>;
};

export type AutorizacionFormGroup = FormGroup<AutorizacionFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AutorizacionFormService {
  createAutorizacionFormGroup(autorizacion: AutorizacionFormGroupInput = { id: null }): AutorizacionFormGroup {
    const autorizacionRawValue = this.convertAutorizacionToAutorizacionRawValue({
      ...this.getFormDefaults(),
      ...autorizacion,
    });
    return new FormGroup<AutorizacionFormGroupContent>({
      id: new FormControl(
        { value: autorizacionRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      tipAutorizacion: new FormControl(autorizacionRawValue.tipAutorizacion, {
        validators: [Validators.required],
      }),
      subTipAutorizacion: new FormControl(autorizacionRawValue.subTipAutorizacion, {
        validators: [Validators.required],
      }),
      concepto: new FormControl(autorizacionRawValue.concepto),
      comentario: new FormControl(autorizacionRawValue.comentario),
      monto: new FormControl(autorizacionRawValue.monto),
      moneda: new FormControl(autorizacionRawValue.moneda),
      token: new FormControl(autorizacionRawValue.token, {
        validators: [Validators.required],
      }),
      fecIniVig: new FormControl(autorizacionRawValue.fecIniVig, {
        validators: [Validators.required],
      }),
      fecFinVig: new FormControl(autorizacionRawValue.fecFinVig, {
        validators: [Validators.required],
      }),
      estado: new FormControl(autorizacionRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(autorizacionRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(autorizacionRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(autorizacionRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(autorizacionRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(autorizacionRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(autorizacionRawValue.fecModif),
      usuModif: new FormControl(autorizacionRawValue.usuModif),
      ipModif: new FormControl(autorizacionRawValue.ipModif),
      producto: new FormControl(autorizacionRawValue.producto),
    });
  }

  getAutorizacion(form: AutorizacionFormGroup): IAutorizacion | NewAutorizacion {
    return this.convertAutorizacionRawValueToAutorizacion(form.getRawValue() as AutorizacionFormRawValue | NewAutorizacionFormRawValue);
  }

  resetForm(form: AutorizacionFormGroup, autorizacion: AutorizacionFormGroupInput): void {
    const autorizacionRawValue = this.convertAutorizacionToAutorizacionRawValue({ ...this.getFormDefaults(), ...autorizacion });
    form.reset(
      {
        ...autorizacionRawValue,
        id: { value: autorizacionRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): AutorizacionFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecIniVig: currentTime,
      fecFinVig: currentTime,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertAutorizacionRawValueToAutorizacion(
    rawAutorizacion: AutorizacionFormRawValue | NewAutorizacionFormRawValue
  ): IAutorizacion | NewAutorizacion {
    return {
      ...rawAutorizacion,
      fecIniVig: dayjs(rawAutorizacion.fecIniVig, DATE_TIME_FORMAT),
      fecFinVig: dayjs(rawAutorizacion.fecFinVig, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawAutorizacion.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawAutorizacion.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertAutorizacionToAutorizacionRawValue(
    autorizacion: IAutorizacion | (Partial<NewAutorizacion> & AutorizacionFormDefaults)
  ): AutorizacionFormRawValue | PartialWithRequiredKeyOf<NewAutorizacionFormRawValue> {
    return {
      ...autorizacion,
      fecIniVig: autorizacion.fecIniVig ? autorizacion.fecIniVig.format(DATE_TIME_FORMAT) : undefined,
      fecFinVig: autorizacion.fecFinVig ? autorizacion.fecFinVig.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: autorizacion.fecCrea ? autorizacion.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: autorizacion.fecModif ? autorizacion.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
