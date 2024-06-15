import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAsignacionCaja, NewAsignacionCaja } from '../asignacion-caja.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAsignacionCaja for edit and NewAsignacionCajaFormGroupInput for create.
 */
type AsignacionCajaFormGroupInput = IAsignacionCaja | PartialWithRequiredKeyOf<NewAsignacionCaja>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IAsignacionCaja | NewAsignacionCaja> = Omit<T, 'fecAsignacion' | 'fecCrea' | 'fecModif'> & {
  fecAsignacion?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type AsignacionCajaFormRawValue = FormValueOf<IAsignacionCaja>;

type NewAsignacionCajaFormRawValue = FormValueOf<NewAsignacionCaja>;

type AsignacionCajaFormDefaults = Pick<NewAsignacionCaja, 'id' | 'fecAsignacion' | 'indDel' | 'fecCrea' | 'fecModif'>;

type AsignacionCajaFormGroupContent = {
  id: FormControl<AsignacionCajaFormRawValue['id'] | NewAsignacionCaja['id']>;
  codAsignacion: FormControl<AsignacionCajaFormRawValue['codAsignacion']>;
  mntoInicialSoles: FormControl<AsignacionCajaFormRawValue['mntoInicialSoles']>;
  mntoFinalSoles: FormControl<AsignacionCajaFormRawValue['mntoFinalSoles']>;
  montoMaximoSoles: FormControl<AsignacionCajaFormRawValue['montoMaximoSoles']>;
  diferenciaSoles: FormControl<AsignacionCajaFormRawValue['diferenciaSoles']>;
  mntoInicialDolares: FormControl<AsignacionCajaFormRawValue['mntoInicialDolares']>;
  mntoFinalDolares: FormControl<AsignacionCajaFormRawValue['mntoFinalDolares']>;
  montoMaximoDolares: FormControl<AsignacionCajaFormRawValue['montoMaximoDolares']>;
  diferenciaDolares: FormControl<AsignacionCajaFormRawValue['diferenciaDolares']>;
  observacionApertura: FormControl<AsignacionCajaFormRawValue['observacionApertura']>;
  observacionCierre: FormControl<AsignacionCajaFormRawValue['observacionCierre']>;
  fecAsignacion: FormControl<AsignacionCajaFormRawValue['fecAsignacion']>;
  estado: FormControl<AsignacionCajaFormRawValue['estado']>;
  version: FormControl<AsignacionCajaFormRawValue['version']>;
  indDel: FormControl<AsignacionCajaFormRawValue['indDel']>;
  fecCrea: FormControl<AsignacionCajaFormRawValue['fecCrea']>;
  usuCrea: FormControl<AsignacionCajaFormRawValue['usuCrea']>;
  ipCrea: FormControl<AsignacionCajaFormRawValue['ipCrea']>;
  fecModif: FormControl<AsignacionCajaFormRawValue['fecModif']>;
  usuModif: FormControl<AsignacionCajaFormRawValue['usuModif']>;
  ipModif: FormControl<AsignacionCajaFormRawValue['ipModif']>;
  userId: FormControl<AsignacionCajaFormRawValue['userId']>;
  caja: FormControl<AsignacionCajaFormRawValue['caja']>;
};

export type AsignacionCajaFormGroup = FormGroup<AsignacionCajaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AsignacionCajaFormService {
  createAsignacionCajaFormGroup(asignacionCaja: AsignacionCajaFormGroupInput = { id: null }): AsignacionCajaFormGroup {
    const asignacionCajaRawValue = this.convertAsignacionCajaToAsignacionCajaRawValue({
      ...this.getFormDefaults(),
      ...asignacionCaja,
    });
    return new FormGroup<AsignacionCajaFormGroupContent>({
      id: new FormControl(
        { value: asignacionCajaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      codAsignacion: new FormControl(asignacionCajaRawValue.codAsignacion, {
        validators: [Validators.required],
      }),
      mntoInicialSoles: new FormControl(asignacionCajaRawValue.mntoInicialSoles),
      mntoFinalSoles: new FormControl(asignacionCajaRawValue.mntoFinalSoles),
      montoMaximoSoles: new FormControl(asignacionCajaRawValue.montoMaximoSoles),
      diferenciaSoles: new FormControl(asignacionCajaRawValue.diferenciaSoles),
      mntoInicialDolares: new FormControl(asignacionCajaRawValue.mntoInicialDolares),
      mntoFinalDolares: new FormControl(asignacionCajaRawValue.mntoFinalDolares),
      montoMaximoDolares: new FormControl(asignacionCajaRawValue.montoMaximoDolares),
      diferenciaDolares: new FormControl(asignacionCajaRawValue.diferenciaDolares),
      observacionApertura: new FormControl(asignacionCajaRawValue.observacionApertura),
      observacionCierre: new FormControl(asignacionCajaRawValue.observacionCierre),
      fecAsignacion: new FormControl(asignacionCajaRawValue.fecAsignacion),
      estado: new FormControl(asignacionCajaRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(asignacionCajaRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(asignacionCajaRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(asignacionCajaRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(asignacionCajaRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(asignacionCajaRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(asignacionCajaRawValue.fecModif),
      usuModif: new FormControl(asignacionCajaRawValue.usuModif),
      ipModif: new FormControl(asignacionCajaRawValue.ipModif),
      userId: new FormControl(asignacionCajaRawValue.userId),
      caja: new FormControl(asignacionCajaRawValue.caja),
    });
  }

  getAsignacionCaja(form: AsignacionCajaFormGroup): IAsignacionCaja | NewAsignacionCaja {
    return this.convertAsignacionCajaRawValueToAsignacionCaja(
      form.getRawValue() as AsignacionCajaFormRawValue | NewAsignacionCajaFormRawValue
    );
  }

  resetForm(form: AsignacionCajaFormGroup, asignacionCaja: AsignacionCajaFormGroupInput): void {
    const asignacionCajaRawValue = this.convertAsignacionCajaToAsignacionCajaRawValue({ ...this.getFormDefaults(), ...asignacionCaja });
    form.reset(
      {
        ...asignacionCajaRawValue,
        id: { value: asignacionCajaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): AsignacionCajaFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecAsignacion: currentTime,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertAsignacionCajaRawValueToAsignacionCaja(
    rawAsignacionCaja: AsignacionCajaFormRawValue | NewAsignacionCajaFormRawValue
  ): IAsignacionCaja | NewAsignacionCaja {
    return {
      ...rawAsignacionCaja,
      fecAsignacion: dayjs(rawAsignacionCaja.fecAsignacion, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawAsignacionCaja.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawAsignacionCaja.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertAsignacionCajaToAsignacionCajaRawValue(
    asignacionCaja: IAsignacionCaja | (Partial<NewAsignacionCaja> & AsignacionCajaFormDefaults)
  ): AsignacionCajaFormRawValue | PartialWithRequiredKeyOf<NewAsignacionCajaFormRawValue> {
    return {
      ...asignacionCaja,
      fecAsignacion: asignacionCaja.fecAsignacion ? asignacionCaja.fecAsignacion.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: asignacionCaja.fecCrea ? asignacionCaja.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: asignacionCaja.fecModif ? asignacionCaja.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
