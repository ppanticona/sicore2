import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IDetallePrecuenta, NewDetallePrecuenta } from '../detalle-precuenta.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDetallePrecuenta for edit and NewDetallePrecuentaFormGroupInput for create.
 */
type DetallePrecuentaFormGroupInput = IDetallePrecuenta | PartialWithRequiredKeyOf<NewDetallePrecuenta>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IDetallePrecuenta | NewDetallePrecuenta> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

type DetallePrecuentaFormRawValue = FormValueOf<IDetallePrecuenta>;

type NewDetallePrecuentaFormRawValue = FormValueOf<NewDetallePrecuenta>;

type DetallePrecuentaFormDefaults = Pick<NewDetallePrecuenta, 'id' | 'indDel' | 'fecCrea' | 'fecModif'>;

type DetallePrecuentaFormGroupContent = {
  id: FormControl<DetallePrecuentaFormRawValue['id'] | NewDetallePrecuenta['id']>;
  correlativo: FormControl<DetallePrecuentaFormRawValue['correlativo']>;
  estado: FormControl<DetallePrecuentaFormRawValue['estado']>;
  version: FormControl<DetallePrecuentaFormRawValue['version']>;
  indDel: FormControl<DetallePrecuentaFormRawValue['indDel']>;
  fecCrea: FormControl<DetallePrecuentaFormRawValue['fecCrea']>;
  usuCrea: FormControl<DetallePrecuentaFormRawValue['usuCrea']>;
  ipCrea: FormControl<DetallePrecuentaFormRawValue['ipCrea']>;
  fecModif: FormControl<DetallePrecuentaFormRawValue['fecModif']>;
  usuModif: FormControl<DetallePrecuentaFormRawValue['usuModif']>;
  ipModif: FormControl<DetallePrecuentaFormRawValue['ipModif']>;
  orden: FormControl<DetallePrecuentaFormRawValue['orden']>;
  autorizacion: FormControl<DetallePrecuentaFormRawValue['autorizacion']>;
  regVenta: FormControl<DetallePrecuentaFormRawValue['regVenta']>;
  precuenta: FormControl<DetallePrecuentaFormRawValue['precuenta']>;
  detalleOrden: FormControl<DetallePrecuentaFormRawValue['detalleOrden']>;
};

export type DetallePrecuentaFormGroup = FormGroup<DetallePrecuentaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DetallePrecuentaFormService {
  createDetallePrecuentaFormGroup(detallePrecuenta: DetallePrecuentaFormGroupInput = { id: null }): DetallePrecuentaFormGroup {
    const detallePrecuentaRawValue = this.convertDetallePrecuentaToDetallePrecuentaRawValue({
      ...this.getFormDefaults(),
      ...detallePrecuenta,
    });
    return new FormGroup<DetallePrecuentaFormGroupContent>({
      id: new FormControl(
        { value: detallePrecuentaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      correlativo: new FormControl(detallePrecuentaRawValue.correlativo, {
        validators: [Validators.required],
      }),
      estado: new FormControl(detallePrecuentaRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(detallePrecuentaRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(detallePrecuentaRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(detallePrecuentaRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(detallePrecuentaRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(detallePrecuentaRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(detallePrecuentaRawValue.fecModif),
      usuModif: new FormControl(detallePrecuentaRawValue.usuModif),
      ipModif: new FormControl(detallePrecuentaRawValue.ipModif),
      orden: new FormControl(detallePrecuentaRawValue.orden),
      autorizacion: new FormControl(detallePrecuentaRawValue.autorizacion),
      regVenta: new FormControl(detallePrecuentaRawValue.regVenta),
      precuenta: new FormControl(detallePrecuentaRawValue.precuenta),
      detalleOrden: new FormControl(detallePrecuentaRawValue.detalleOrden),
    });
  }

  getDetallePrecuenta(form: DetallePrecuentaFormGroup): IDetallePrecuenta | NewDetallePrecuenta {
    return this.convertDetallePrecuentaRawValueToDetallePrecuenta(
      form.getRawValue() as DetallePrecuentaFormRawValue | NewDetallePrecuentaFormRawValue
    );
  }

  resetForm(form: DetallePrecuentaFormGroup, detallePrecuenta: DetallePrecuentaFormGroupInput): void {
    const detallePrecuentaRawValue = this.convertDetallePrecuentaToDetallePrecuentaRawValue({
      ...this.getFormDefaults(),
      ...detallePrecuenta,
    });
    form.reset(
      {
        ...detallePrecuentaRawValue,
        id: { value: detallePrecuentaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DetallePrecuentaFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertDetallePrecuentaRawValueToDetallePrecuenta(
    rawDetallePrecuenta: DetallePrecuentaFormRawValue | NewDetallePrecuentaFormRawValue
  ): IDetallePrecuenta | NewDetallePrecuenta {
    return {
      ...rawDetallePrecuenta,
      fecCrea: dayjs(rawDetallePrecuenta.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawDetallePrecuenta.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertDetallePrecuentaToDetallePrecuentaRawValue(
    detallePrecuenta: IDetallePrecuenta | (Partial<NewDetallePrecuenta> & DetallePrecuentaFormDefaults)
  ): DetallePrecuentaFormRawValue | PartialWithRequiredKeyOf<NewDetallePrecuentaFormRawValue> {
    return {
      ...detallePrecuenta,
      fecCrea: detallePrecuenta.fecCrea ? detallePrecuenta.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: detallePrecuenta.fecModif ? detallePrecuenta.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
