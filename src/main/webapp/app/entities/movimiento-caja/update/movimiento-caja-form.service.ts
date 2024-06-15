import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IMovimientoCaja, NewMovimientoCaja } from '../movimiento-caja.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMovimientoCaja for edit and NewMovimientoCajaFormGroupInput for create.
 */
type MovimientoCajaFormGroupInput = IMovimientoCaja | PartialWithRequiredKeyOf<NewMovimientoCaja>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IMovimientoCaja | NewMovimientoCaja> = Omit<T, 'fecMovimiento' | 'fecCrea' | 'fecModif'> & {
  fecMovimiento?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type MovimientoCajaFormRawValue = FormValueOf<IMovimientoCaja>;

type NewMovimientoCajaFormRawValue = FormValueOf<NewMovimientoCaja>;

type MovimientoCajaFormDefaults = Pick<NewMovimientoCaja, 'id' | 'fecMovimiento' | 'indDel' | 'fecCrea' | 'fecModif'>;

type MovimientoCajaFormGroupContent = {
  id: FormControl<MovimientoCajaFormRawValue['id'] | NewMovimientoCaja['id']>;
  tipMovimiento: FormControl<MovimientoCajaFormRawValue['tipMovimiento']>;
  concepto: FormControl<MovimientoCajaFormRawValue['concepto']>;
  monto: FormControl<MovimientoCajaFormRawValue['monto']>;
  tipMoneda: FormControl<MovimientoCajaFormRawValue['tipMoneda']>;
  formPago: FormControl<MovimientoCajaFormRawValue['formPago']>;
  comprobante: FormControl<MovimientoCajaFormRawValue['comprobante']>;
  fecMovimiento: FormControl<MovimientoCajaFormRawValue['fecMovimiento']>;
  version: FormControl<MovimientoCajaFormRawValue['version']>;
  indDel: FormControl<MovimientoCajaFormRawValue['indDel']>;
  fecCrea: FormControl<MovimientoCajaFormRawValue['fecCrea']>;
  usuCrea: FormControl<MovimientoCajaFormRawValue['usuCrea']>;
  ipCrea: FormControl<MovimientoCajaFormRawValue['ipCrea']>;
  fecModif: FormControl<MovimientoCajaFormRawValue['fecModif']>;
  usuModif: FormControl<MovimientoCajaFormRawValue['usuModif']>;
  ipModif: FormControl<MovimientoCajaFormRawValue['ipModif']>;
  asignacionCaja: FormControl<MovimientoCajaFormRawValue['asignacionCaja']>;
  autorizacion: FormControl<MovimientoCajaFormRawValue['autorizacion']>;
};

export type MovimientoCajaFormGroup = FormGroup<MovimientoCajaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MovimientoCajaFormService {
  createMovimientoCajaFormGroup(movimientoCaja: MovimientoCajaFormGroupInput = { id: null }): MovimientoCajaFormGroup {
    const movimientoCajaRawValue = this.convertMovimientoCajaToMovimientoCajaRawValue({
      ...this.getFormDefaults(),
      ...movimientoCaja,
    });
    return new FormGroup<MovimientoCajaFormGroupContent>({
      id: new FormControl(
        { value: movimientoCajaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      tipMovimiento: new FormControl(movimientoCajaRawValue.tipMovimiento),
      concepto: new FormControl(movimientoCajaRawValue.concepto),
      monto: new FormControl(movimientoCajaRawValue.monto),
      tipMoneda: new FormControl(movimientoCajaRawValue.tipMoneda),
      formPago: new FormControl(movimientoCajaRawValue.formPago),
      comprobante: new FormControl(movimientoCajaRawValue.comprobante),
      fecMovimiento: new FormControl(movimientoCajaRawValue.fecMovimiento),
      version: new FormControl(movimientoCajaRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(movimientoCajaRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(movimientoCajaRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(movimientoCajaRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(movimientoCajaRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(movimientoCajaRawValue.fecModif),
      usuModif: new FormControl(movimientoCajaRawValue.usuModif),
      ipModif: new FormControl(movimientoCajaRawValue.ipModif),
      asignacionCaja: new FormControl(movimientoCajaRawValue.asignacionCaja),
      autorizacion: new FormControl(movimientoCajaRawValue.autorizacion),
    });
  }

  getMovimientoCaja(form: MovimientoCajaFormGroup): IMovimientoCaja | NewMovimientoCaja {
    return this.convertMovimientoCajaRawValueToMovimientoCaja(
      form.getRawValue() as MovimientoCajaFormRawValue | NewMovimientoCajaFormRawValue
    );
  }

  resetForm(form: MovimientoCajaFormGroup, movimientoCaja: MovimientoCajaFormGroupInput): void {
    const movimientoCajaRawValue = this.convertMovimientoCajaToMovimientoCajaRawValue({ ...this.getFormDefaults(), ...movimientoCaja });
    form.reset(
      {
        ...movimientoCajaRawValue,
        id: { value: movimientoCajaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): MovimientoCajaFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecMovimiento: currentTime,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertMovimientoCajaRawValueToMovimientoCaja(
    rawMovimientoCaja: MovimientoCajaFormRawValue | NewMovimientoCajaFormRawValue
  ): IMovimientoCaja | NewMovimientoCaja {
    return {
      ...rawMovimientoCaja,
      fecMovimiento: dayjs(rawMovimientoCaja.fecMovimiento, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawMovimientoCaja.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawMovimientoCaja.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertMovimientoCajaToMovimientoCajaRawValue(
    movimientoCaja: IMovimientoCaja | (Partial<NewMovimientoCaja> & MovimientoCajaFormDefaults)
  ): MovimientoCajaFormRawValue | PartialWithRequiredKeyOf<NewMovimientoCajaFormRawValue> {
    return {
      ...movimientoCaja,
      fecMovimiento: movimientoCaja.fecMovimiento ? movimientoCaja.fecMovimiento.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: movimientoCaja.fecCrea ? movimientoCaja.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: movimientoCaja.fecModif ? movimientoCaja.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
