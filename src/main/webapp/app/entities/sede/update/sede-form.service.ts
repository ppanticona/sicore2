import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ISede, NewSede } from '../sede.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISede for edit and NewSedeFormGroupInput for create.
 */
type SedeFormGroupInput = ISede | PartialWithRequiredKeyOf<NewSede>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ISede | NewSede> = Omit<T, 'fecAper' | 'fecCrea' | 'fecModif'> & {
  fecAper?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type SedeFormRawValue = FormValueOf<ISede>;

type NewSedeFormRawValue = FormValueOf<NewSede>;

type SedeFormDefaults = Pick<NewSede, 'id' | 'fecAper' | 'indDel' | 'fecCrea' | 'fecModif'>;

type SedeFormGroupContent = {
  id: FormControl<SedeFormRawValue['id'] | NewSede['id']>;
  codSede: FormControl<SedeFormRawValue['codSede']>;
  descripcion: FormControl<SedeFormRawValue['descripcion']>;
  categoria: FormControl<SedeFormRawValue['categoria']>;
  tel1: FormControl<SedeFormRawValue['tel1']>;
  tel2: FormControl<SedeFormRawValue['tel2']>;
  correo: FormControl<SedeFormRawValue['correo']>;
  direccion: FormControl<SedeFormRawValue['direccion']>;
  refDirecc: FormControl<SedeFormRawValue['refDirecc']>;
  distrito: FormControl<SedeFormRawValue['distrito']>;
  fecAper: FormControl<SedeFormRawValue['fecAper']>;
  estado: FormControl<SedeFormRawValue['estado']>;
  version: FormControl<SedeFormRawValue['version']>;
  indDel: FormControl<SedeFormRawValue['indDel']>;
  fecCrea: FormControl<SedeFormRawValue['fecCrea']>;
  usuCrea: FormControl<SedeFormRawValue['usuCrea']>;
  ipCrea: FormControl<SedeFormRawValue['ipCrea']>;
  fecModif: FormControl<SedeFormRawValue['fecModif']>;
  usuModif: FormControl<SedeFormRawValue['usuModif']>;
  ipModif: FormControl<SedeFormRawValue['ipModif']>;
};

export type SedeFormGroup = FormGroup<SedeFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SedeFormService {
  createSedeFormGroup(sede: SedeFormGroupInput = { id: null }): SedeFormGroup {
    const sedeRawValue = this.convertSedeToSedeRawValue({
      ...this.getFormDefaults(),
      ...sede,
    });
    return new FormGroup<SedeFormGroupContent>({
      id: new FormControl(
        { value: sedeRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      codSede: new FormControl(sedeRawValue.codSede, {
        validators: [Validators.required],
      }),
      descripcion: new FormControl(sedeRawValue.descripcion, {
        validators: [Validators.required],
      }),
      categoria: new FormControl(sedeRawValue.categoria),
      tel1: new FormControl(sedeRawValue.tel1),
      tel2: new FormControl(sedeRawValue.tel2),
      correo: new FormControl(sedeRawValue.correo),
      direccion: new FormControl(sedeRawValue.direccion),
      refDirecc: new FormControl(sedeRawValue.refDirecc),
      distrito: new FormControl(sedeRawValue.distrito),
      fecAper: new FormControl(sedeRawValue.fecAper, {
        validators: [Validators.required],
      }),
      estado: new FormControl(sedeRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(sedeRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(sedeRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(sedeRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(sedeRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(sedeRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(sedeRawValue.fecModif),
      usuModif: new FormControl(sedeRawValue.usuModif),
      ipModif: new FormControl(sedeRawValue.ipModif),
    });
  }

  getSede(form: SedeFormGroup): ISede | NewSede {
    return this.convertSedeRawValueToSede(form.getRawValue() as SedeFormRawValue | NewSedeFormRawValue);
  }

  resetForm(form: SedeFormGroup, sede: SedeFormGroupInput): void {
    const sedeRawValue = this.convertSedeToSedeRawValue({ ...this.getFormDefaults(), ...sede });
    form.reset(
      {
        ...sedeRawValue,
        id: { value: sedeRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SedeFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecAper: currentTime,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertSedeRawValueToSede(rawSede: SedeFormRawValue | NewSedeFormRawValue): ISede | NewSede {
    return {
      ...rawSede,
      fecAper: dayjs(rawSede.fecAper, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawSede.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawSede.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertSedeToSedeRawValue(
    sede: ISede | (Partial<NewSede> & SedeFormDefaults)
  ): SedeFormRawValue | PartialWithRequiredKeyOf<NewSedeFormRawValue> {
    return {
      ...sede,
      fecAper: sede.fecAper ? sede.fecAper.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: sede.fecCrea ? sede.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: sede.fecModif ? sede.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
