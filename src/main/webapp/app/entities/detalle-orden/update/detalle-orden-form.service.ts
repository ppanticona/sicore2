import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IDetalleOrden, NewDetalleOrden } from '../detalle-orden.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDetalleOrden for edit and NewDetalleOrdenFormGroupInput for create.
 */
type DetalleOrdenFormGroupInput = IDetalleOrden | PartialWithRequiredKeyOf<NewDetalleOrden>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IDetalleOrden | NewDetalleOrden> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

type DetalleOrdenFormRawValue = FormValueOf<IDetalleOrden>;

type NewDetalleOrdenFormRawValue = FormValueOf<NewDetalleOrden>;

type DetalleOrdenFormDefaults = Pick<NewDetalleOrden, 'id' | 'indPagado' | 'indDel' | 'fecCrea' | 'fecModif'>;

type DetalleOrdenFormGroupContent = {
  id: FormControl<DetalleOrdenFormRawValue['id'] | NewDetalleOrden['id']>;
  observacion: FormControl<DetalleOrdenFormRawValue['observacion']>;
  monto: FormControl<DetalleOrdenFormRawValue['monto']>;
  indPagado: FormControl<DetalleOrdenFormRawValue['indPagado']>;
  estado: FormControl<DetalleOrdenFormRawValue['estado']>;
  version: FormControl<DetalleOrdenFormRawValue['version']>;
  indDel: FormControl<DetalleOrdenFormRawValue['indDel']>;
  fecCrea: FormControl<DetalleOrdenFormRawValue['fecCrea']>;
  usuCrea: FormControl<DetalleOrdenFormRawValue['usuCrea']>;
  ipCrea: FormControl<DetalleOrdenFormRawValue['ipCrea']>;
  fecModif: FormControl<DetalleOrdenFormRawValue['fecModif']>;
  usuModif: FormControl<DetalleOrdenFormRawValue['usuModif']>;
  ipModif: FormControl<DetalleOrdenFormRawValue['ipModif']>;
  orden: FormControl<DetalleOrdenFormRawValue['orden']>;
  promocion: FormControl<DetalleOrdenFormRawValue['promocion']>;
  autorizacion: FormControl<DetalleOrdenFormRawValue['autorizacion']>;
  producto: FormControl<DetalleOrdenFormRawValue['producto']>;
};

export type DetalleOrdenFormGroup = FormGroup<DetalleOrdenFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DetalleOrdenFormService {
  createDetalleOrdenFormGroup(detalleOrden: DetalleOrdenFormGroupInput = { id: null }): DetalleOrdenFormGroup {
    const detalleOrdenRawValue = this.convertDetalleOrdenToDetalleOrdenRawValue({
      ...this.getFormDefaults(),
      ...detalleOrden,
    });
    return new FormGroup<DetalleOrdenFormGroupContent>({
      id: new FormControl(
        { value: detalleOrdenRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      observacion: new FormControl(detalleOrdenRawValue.observacion),
      monto: new FormControl(detalleOrdenRawValue.monto),
      indPagado: new FormControl(detalleOrdenRawValue.indPagado),
      estado: new FormControl(detalleOrdenRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(detalleOrdenRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(detalleOrdenRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(detalleOrdenRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(detalleOrdenRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(detalleOrdenRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(detalleOrdenRawValue.fecModif),
      usuModif: new FormControl(detalleOrdenRawValue.usuModif),
      ipModif: new FormControl(detalleOrdenRawValue.ipModif),
      orden: new FormControl(detalleOrdenRawValue.orden),
      promocion: new FormControl(detalleOrdenRawValue.promocion),
      autorizacion: new FormControl(detalleOrdenRawValue.autorizacion),
      producto: new FormControl(detalleOrdenRawValue.producto),
    });
  }

  getDetalleOrden(form: DetalleOrdenFormGroup): IDetalleOrden | NewDetalleOrden {
    return this.convertDetalleOrdenRawValueToDetalleOrden(form.getRawValue() as DetalleOrdenFormRawValue | NewDetalleOrdenFormRawValue);
  }

  resetForm(form: DetalleOrdenFormGroup, detalleOrden: DetalleOrdenFormGroupInput): void {
    const detalleOrdenRawValue = this.convertDetalleOrdenToDetalleOrdenRawValue({ ...this.getFormDefaults(), ...detalleOrden });
    form.reset(
      {
        ...detalleOrdenRawValue,
        id: { value: detalleOrdenRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DetalleOrdenFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      indPagado: false,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertDetalleOrdenRawValueToDetalleOrden(
    rawDetalleOrden: DetalleOrdenFormRawValue | NewDetalleOrdenFormRawValue
  ): IDetalleOrden | NewDetalleOrden {
    return {
      ...rawDetalleOrden,
      fecCrea: dayjs(rawDetalleOrden.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawDetalleOrden.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertDetalleOrdenToDetalleOrdenRawValue(
    detalleOrden: IDetalleOrden | (Partial<NewDetalleOrden> & DetalleOrdenFormDefaults)
  ): DetalleOrdenFormRawValue | PartialWithRequiredKeyOf<NewDetalleOrdenFormRawValue> {
    return {
      ...detalleOrden,
      fecCrea: detalleOrden.fecCrea ? detalleOrden.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: detalleOrden.fecModif ? detalleOrden.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
