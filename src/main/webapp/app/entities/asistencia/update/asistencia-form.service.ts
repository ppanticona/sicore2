import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAsistencia, NewAsistencia } from '../asistencia.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAsistencia for edit and NewAsistenciaFormGroupInput for create.
 */
type AsistenciaFormGroupInput = IAsistencia | PartialWithRequiredKeyOf<NewAsistencia>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IAsistencia | NewAsistencia> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

type AsistenciaFormRawValue = FormValueOf<IAsistencia>;

type NewAsistenciaFormRawValue = FormValueOf<NewAsistencia>;

type AsistenciaFormDefaults = Pick<NewAsistencia, 'id' | 'indDel' | 'fecCrea' | 'fecModif'>;

type AsistenciaFormGroupContent = {
  id: FormControl<AsistenciaFormRawValue['id'] | NewAsistencia['id']>;
  tipAsistente: FormControl<AsistenciaFormRawValue['tipAsistente']>;
  resultado: FormControl<AsistenciaFormRawValue['resultado']>;
  anoAsistencia: FormControl<AsistenciaFormRawValue['anoAsistencia']>;
  mesAsistencia: FormControl<AsistenciaFormRawValue['mesAsistencia']>;
  diaAsistencia: FormControl<AsistenciaFormRawValue['diaAsistencia']>;
  observacion: FormControl<AsistenciaFormRawValue['observacion']>;
  comentarios: FormControl<AsistenciaFormRawValue['comentarios']>;
  estado: FormControl<AsistenciaFormRawValue['estado']>;
  version: FormControl<AsistenciaFormRawValue['version']>;
  indDel: FormControl<AsistenciaFormRawValue['indDel']>;
  fecCrea: FormControl<AsistenciaFormRawValue['fecCrea']>;
  usuCrea: FormControl<AsistenciaFormRawValue['usuCrea']>;
  ipCrea: FormControl<AsistenciaFormRawValue['ipCrea']>;
  fecModif: FormControl<AsistenciaFormRawValue['fecModif']>;
  usuModif: FormControl<AsistenciaFormRawValue['usuModif']>;
  ipModif: FormControl<AsistenciaFormRawValue['ipModif']>;
  userId: FormControl<AsistenciaFormRawValue['userId']>;
};

export type AsistenciaFormGroup = FormGroup<AsistenciaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AsistenciaFormService {
  createAsistenciaFormGroup(asistencia: AsistenciaFormGroupInput = { id: null }): AsistenciaFormGroup {
    const asistenciaRawValue = this.convertAsistenciaToAsistenciaRawValue({
      ...this.getFormDefaults(),
      ...asistencia,
    });
    return new FormGroup<AsistenciaFormGroupContent>({
      id: new FormControl(
        { value: asistenciaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      tipAsistente: new FormControl(asistenciaRawValue.tipAsistente, {
        validators: [Validators.required],
      }),
      resultado: new FormControl(asistenciaRawValue.resultado),
      anoAsistencia: new FormControl(asistenciaRawValue.anoAsistencia),
      mesAsistencia: new FormControl(asistenciaRawValue.mesAsistencia),
      diaAsistencia: new FormControl(asistenciaRawValue.diaAsistencia),
      observacion: new FormControl(asistenciaRawValue.observacion),
      comentarios: new FormControl(asistenciaRawValue.comentarios, {
        validators: [Validators.required],
      }),
      estado: new FormControl(asistenciaRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(asistenciaRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(asistenciaRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(asistenciaRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(asistenciaRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(asistenciaRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(asistenciaRawValue.fecModif),
      usuModif: new FormControl(asistenciaRawValue.usuModif),
      ipModif: new FormControl(asistenciaRawValue.ipModif),
      userId: new FormControl(asistenciaRawValue.userId),
    });
  }

  getAsistencia(form: AsistenciaFormGroup): IAsistencia | NewAsistencia {
    return this.convertAsistenciaRawValueToAsistencia(form.getRawValue() as AsistenciaFormRawValue | NewAsistenciaFormRawValue);
  }

  resetForm(form: AsistenciaFormGroup, asistencia: AsistenciaFormGroupInput): void {
    const asistenciaRawValue = this.convertAsistenciaToAsistenciaRawValue({ ...this.getFormDefaults(), ...asistencia });
    form.reset(
      {
        ...asistenciaRawValue,
        id: { value: asistenciaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): AsistenciaFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertAsistenciaRawValueToAsistencia(
    rawAsistencia: AsistenciaFormRawValue | NewAsistenciaFormRawValue
  ): IAsistencia | NewAsistencia {
    return {
      ...rawAsistencia,
      fecCrea: dayjs(rawAsistencia.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawAsistencia.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertAsistenciaToAsistenciaRawValue(
    asistencia: IAsistencia | (Partial<NewAsistencia> & AsistenciaFormDefaults)
  ): AsistenciaFormRawValue | PartialWithRequiredKeyOf<NewAsistenciaFormRawValue> {
    return {
      ...asistencia,
      fecCrea: asistencia.fecCrea ? asistencia.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: asistencia.fecModif ? asistencia.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
