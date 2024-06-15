import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IMesas, NewMesas } from '../mesas.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMesas for edit and NewMesasFormGroupInput for create.
 */
type MesasFormGroupInput = IMesas | PartialWithRequiredKeyOf<NewMesas>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IMesas | NewMesas> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

type MesasFormRawValue = FormValueOf<IMesas>;

type NewMesasFormRawValue = FormValueOf<NewMesas>;

type MesasFormDefaults = Pick<NewMesas, 'id' | 'indMesaJunta' | 'indDel' | 'fecCrea' | 'fecModif'>;

type MesasFormGroupContent = {
  id: FormControl<MesasFormRawValue['id'] | NewMesas['id']>;
  seqMesa: FormControl<MesasFormRawValue['seqMesa']>;
  nroMesa: FormControl<MesasFormRawValue['nroMesa']>;
  codGrupo: FormControl<MesasFormRawValue['codGrupo']>;
  categoria: FormControl<MesasFormRawValue['categoria']>;
  capacidad: FormControl<MesasFormRawValue['capacidad']>;
  indMesaJunta: FormControl<MesasFormRawValue['indMesaJunta']>;
  estado: FormControl<MesasFormRawValue['estado']>;
  version: FormControl<MesasFormRawValue['version']>;
  indDel: FormControl<MesasFormRawValue['indDel']>;
  fecCrea: FormControl<MesasFormRawValue['fecCrea']>;
  usuCrea: FormControl<MesasFormRawValue['usuCrea']>;
  ipCrea: FormControl<MesasFormRawValue['ipCrea']>;
  fecModif: FormControl<MesasFormRawValue['fecModif']>;
  usuModif: FormControl<MesasFormRawValue['usuModif']>;
  ipModif: FormControl<MesasFormRawValue['ipModif']>;
  orden: FormControl<MesasFormRawValue['orden']>;
  sede: FormControl<MesasFormRawValue['sede']>;
  empleado: FormControl<MesasFormRawValue['empleado']>;
};

export type MesasFormGroup = FormGroup<MesasFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MesasFormService {
  createMesasFormGroup(mesas: MesasFormGroupInput = { id: null }): MesasFormGroup {
    const mesasRawValue = this.convertMesasToMesasRawValue({
      ...this.getFormDefaults(),
      ...mesas,
    });
    return new FormGroup<MesasFormGroupContent>({
      id: new FormControl(
        { value: mesasRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      seqMesa: new FormControl(mesasRawValue.seqMesa, {
        validators: [Validators.required],
      }),
      nroMesa: new FormControl(mesasRawValue.nroMesa, {
        validators: [Validators.required],
      }),
      codGrupo: new FormControl(mesasRawValue.codGrupo),
      categoria: new FormControl(mesasRawValue.categoria),
      capacidad: new FormControl(mesasRawValue.capacidad),
      indMesaJunta: new FormControl(mesasRawValue.indMesaJunta, {
        validators: [Validators.required],
      }),
      estado: new FormControl(mesasRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(mesasRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(mesasRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(mesasRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(mesasRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(mesasRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(mesasRawValue.fecModif),
      usuModif: new FormControl(mesasRawValue.usuModif),
      ipModif: new FormControl(mesasRawValue.ipModif),
      orden: new FormControl(mesasRawValue.orden),
      sede: new FormControl(mesasRawValue.sede),
      empleado: new FormControl(mesasRawValue.empleado),
    });
  }

  getMesas(form: MesasFormGroup): IMesas | NewMesas {
    return this.convertMesasRawValueToMesas(form.getRawValue() as MesasFormRawValue | NewMesasFormRawValue);
  }

  resetForm(form: MesasFormGroup, mesas: MesasFormGroupInput): void {
    const mesasRawValue = this.convertMesasToMesasRawValue({ ...this.getFormDefaults(), ...mesas });
    form.reset(
      {
        ...mesasRawValue,
        id: { value: mesasRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): MesasFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      indMesaJunta: false,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertMesasRawValueToMesas(rawMesas: MesasFormRawValue | NewMesasFormRawValue): IMesas | NewMesas {
    return {
      ...rawMesas,
      fecCrea: dayjs(rawMesas.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawMesas.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertMesasToMesasRawValue(
    mesas: IMesas | (Partial<NewMesas> & MesasFormDefaults)
  ): MesasFormRawValue | PartialWithRequiredKeyOf<NewMesasFormRawValue> {
    return {
      ...mesas,
      fecCrea: mesas.fecCrea ? mesas.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: mesas.fecModif ? mesas.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
