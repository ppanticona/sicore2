import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IPromocion, NewPromocion } from '../promocion.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPromocion for edit and NewPromocionFormGroupInput for create.
 */
type PromocionFormGroupInput = IPromocion | PartialWithRequiredKeyOf<NewPromocion>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IPromocion | NewPromocion> = Omit<T, 'fecIniVig' | 'fecFinVig' | 'fecCrea' | 'fecModif'> & {
  fecIniVig?: string | null;
  fecFinVig?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type PromocionFormRawValue = FormValueOf<IPromocion>;

type NewPromocionFormRawValue = FormValueOf<NewPromocion>;

type PromocionFormDefaults = Pick<NewPromocion, 'id' | 'fecIniVig' | 'fecFinVig' | 'indDel' | 'fecCrea' | 'fecModif'>;

type PromocionFormGroupContent = {
  id: FormControl<PromocionFormRawValue['id'] | NewPromocion['id']>;
  tipPromocion: FormControl<PromocionFormRawValue['tipPromocion']>;
  val1: FormControl<PromocionFormRawValue['val1']>;
  val2: FormControl<PromocionFormRawValue['val2']>;
  val3: FormControl<PromocionFormRawValue['val3']>;
  val4: FormControl<PromocionFormRawValue['val4']>;
  val5: FormControl<PromocionFormRawValue['val5']>;
  maxProm: FormControl<PromocionFormRawValue['maxProm']>;
  fecIniVig: FormControl<PromocionFormRawValue['fecIniVig']>;
  fecFinVig: FormControl<PromocionFormRawValue['fecFinVig']>;
  comentarios: FormControl<PromocionFormRawValue['comentarios']>;
  estado: FormControl<PromocionFormRawValue['estado']>;
  version: FormControl<PromocionFormRawValue['version']>;
  indDel: FormControl<PromocionFormRawValue['indDel']>;
  fecCrea: FormControl<PromocionFormRawValue['fecCrea']>;
  usuCrea: FormControl<PromocionFormRawValue['usuCrea']>;
  ipCrea: FormControl<PromocionFormRawValue['ipCrea']>;
  fecModif: FormControl<PromocionFormRawValue['fecModif']>;
  usuModif: FormControl<PromocionFormRawValue['usuModif']>;
  ipModif: FormControl<PromocionFormRawValue['ipModif']>;
  autorizacion: FormControl<PromocionFormRawValue['autorizacion']>;
};

export type PromocionFormGroup = FormGroup<PromocionFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PromocionFormService {
  createPromocionFormGroup(promocion: PromocionFormGroupInput = { id: null }): PromocionFormGroup {
    const promocionRawValue = this.convertPromocionToPromocionRawValue({
      ...this.getFormDefaults(),
      ...promocion,
    });
    return new FormGroup<PromocionFormGroupContent>({
      id: new FormControl(
        { value: promocionRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      tipPromocion: new FormControl(promocionRawValue.tipPromocion, {
        validators: [Validators.required],
      }),
      val1: new FormControl(promocionRawValue.val1),
      val2: new FormControl(promocionRawValue.val2),
      val3: new FormControl(promocionRawValue.val3),
      val4: new FormControl(promocionRawValue.val4),
      val5: new FormControl(promocionRawValue.val5),
      maxProm: new FormControl(promocionRawValue.maxProm),
      fecIniVig: new FormControl(promocionRawValue.fecIniVig, {
        validators: [Validators.required],
      }),
      fecFinVig: new FormControl(promocionRawValue.fecFinVig, {
        validators: [Validators.required],
      }),
      comentarios: new FormControl(promocionRawValue.comentarios, {
        validators: [Validators.required],
      }),
      estado: new FormControl(promocionRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(promocionRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(promocionRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(promocionRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(promocionRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(promocionRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(promocionRawValue.fecModif),
      usuModif: new FormControl(promocionRawValue.usuModif),
      ipModif: new FormControl(promocionRawValue.ipModif),
      autorizacion: new FormControl(promocionRawValue.autorizacion),
    });
  }

  getPromocion(form: PromocionFormGroup): IPromocion | NewPromocion {
    return this.convertPromocionRawValueToPromocion(form.getRawValue() as PromocionFormRawValue | NewPromocionFormRawValue);
  }

  resetForm(form: PromocionFormGroup, promocion: PromocionFormGroupInput): void {
    const promocionRawValue = this.convertPromocionToPromocionRawValue({ ...this.getFormDefaults(), ...promocion });
    form.reset(
      {
        ...promocionRawValue,
        id: { value: promocionRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): PromocionFormDefaults {
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

  private convertPromocionRawValueToPromocion(rawPromocion: PromocionFormRawValue | NewPromocionFormRawValue): IPromocion | NewPromocion {
    return {
      ...rawPromocion,
      fecIniVig: dayjs(rawPromocion.fecIniVig, DATE_TIME_FORMAT),
      fecFinVig: dayjs(rawPromocion.fecFinVig, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawPromocion.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawPromocion.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertPromocionToPromocionRawValue(
    promocion: IPromocion | (Partial<NewPromocion> & PromocionFormDefaults)
  ): PromocionFormRawValue | PartialWithRequiredKeyOf<NewPromocionFormRawValue> {
    return {
      ...promocion,
      fecIniVig: promocion.fecIniVig ? promocion.fecIniVig.format(DATE_TIME_FORMAT) : undefined,
      fecFinVig: promocion.fecFinVig ? promocion.fecFinVig.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: promocion.fecCrea ? promocion.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: promocion.fecModif ? promocion.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
