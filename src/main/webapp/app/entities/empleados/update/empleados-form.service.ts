import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IEmpleados, NewEmpleados } from '../empleados.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IEmpleados for edit and NewEmpleadosFormGroupInput for create.
 */
type EmpleadosFormGroupInput = IEmpleados | PartialWithRequiredKeyOf<NewEmpleados>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IEmpleados | NewEmpleados> = Omit<T, 'fecIngreso' | 'fecNac' | 'fecCrea' | 'fecModif'> & {
  fecIngreso?: string | null;
  fecNac?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type EmpleadosFormRawValue = FormValueOf<IEmpleados>;

type NewEmpleadosFormRawValue = FormValueOf<NewEmpleados>;

type EmpleadosFormDefaults = Pick<NewEmpleados, 'id' | 'fecIngreso' | 'fecNac' | 'indDel' | 'fecCrea' | 'fecModif'>;

type EmpleadosFormGroupContent = {
  id: FormControl<EmpleadosFormRawValue['id'] | NewEmpleados['id']>;
  tipDoc: FormControl<EmpleadosFormRawValue['tipDoc']>;
  nroDoc: FormControl<EmpleadosFormRawValue['nroDoc']>;
  nombres: FormControl<EmpleadosFormRawValue['nombres']>;
  apellidos: FormControl<EmpleadosFormRawValue['apellidos']>;
  categoria: FormControl<EmpleadosFormRawValue['categoria']>;
  tel1: FormControl<EmpleadosFormRawValue['tel1']>;
  tel2: FormControl<EmpleadosFormRawValue['tel2']>;
  correo: FormControl<EmpleadosFormRawValue['correo']>;
  direccion: FormControl<EmpleadosFormRawValue['direccion']>;
  refDirecc: FormControl<EmpleadosFormRawValue['refDirecc']>;
  distrito: FormControl<EmpleadosFormRawValue['distrito']>;
  fecIngreso: FormControl<EmpleadosFormRawValue['fecIngreso']>;
  fecNac: FormControl<EmpleadosFormRawValue['fecNac']>;
  imagen: FormControl<EmpleadosFormRawValue['imagen']>;
  userId: FormControl<EmpleadosFormRawValue['userId']>;
  estado: FormControl<EmpleadosFormRawValue['estado']>;
  version: FormControl<EmpleadosFormRawValue['version']>;
  indDel: FormControl<EmpleadosFormRawValue['indDel']>;
  fecCrea: FormControl<EmpleadosFormRawValue['fecCrea']>;
  usuCrea: FormControl<EmpleadosFormRawValue['usuCrea']>;
  ipCrea: FormControl<EmpleadosFormRawValue['ipCrea']>;
  fecModif: FormControl<EmpleadosFormRawValue['fecModif']>;
  usuModif: FormControl<EmpleadosFormRawValue['usuModif']>;
  ipModif: FormControl<EmpleadosFormRawValue['ipModif']>;
  user: FormControl<EmpleadosFormRawValue['user']>;
};

export type EmpleadosFormGroup = FormGroup<EmpleadosFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class EmpleadosFormService {
  createEmpleadosFormGroup(empleados: EmpleadosFormGroupInput = { id: null }): EmpleadosFormGroup {
    const empleadosRawValue = this.convertEmpleadosToEmpleadosRawValue({
      ...this.getFormDefaults(),
      ...empleados,
    });
    return new FormGroup<EmpleadosFormGroupContent>({
      id: new FormControl(
        { value: empleadosRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      tipDoc: new FormControl(empleadosRawValue.tipDoc, {
        validators: [Validators.required],
      }),
      nroDoc: new FormControl(empleadosRawValue.nroDoc, {
        validators: [Validators.required],
      }),
      nombres: new FormControl(empleadosRawValue.nombres, {
        validators: [Validators.required],
      }),
      apellidos: new FormControl(empleadosRawValue.apellidos, {
        validators: [Validators.required],
      }),
      categoria: new FormControl(empleadosRawValue.categoria),
      tel1: new FormControl(empleadosRawValue.tel1),
      tel2: new FormControl(empleadosRawValue.tel2),
      correo: new FormControl(empleadosRawValue.correo),
      direccion: new FormControl(empleadosRawValue.direccion),
      refDirecc: new FormControl(empleadosRawValue.refDirecc),
      distrito: new FormControl(empleadosRawValue.distrito),
      fecIngreso: new FormControl(empleadosRawValue.fecIngreso),
      fecNac: new FormControl(empleadosRawValue.fecNac, {
        validators: [Validators.required],
      }),
      imagen: new FormControl(empleadosRawValue.imagen),
      userId: new FormControl(empleadosRawValue.userId),
      estado: new FormControl(empleadosRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(empleadosRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(empleadosRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(empleadosRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(empleadosRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(empleadosRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(empleadosRawValue.fecModif),
      usuModif: new FormControl(empleadosRawValue.usuModif),
      ipModif: new FormControl(empleadosRawValue.ipModif),
      user: new FormControl(empleadosRawValue.user),
    });
  }

  getEmpleados(form: EmpleadosFormGroup): IEmpleados | NewEmpleados {
    return this.convertEmpleadosRawValueToEmpleados(form.getRawValue() as EmpleadosFormRawValue | NewEmpleadosFormRawValue);
  }

  resetForm(form: EmpleadosFormGroup, empleados: EmpleadosFormGroupInput): void {
    const empleadosRawValue = this.convertEmpleadosToEmpleadosRawValue({ ...this.getFormDefaults(), ...empleados });
    form.reset(
      {
        ...empleadosRawValue,
        id: { value: empleadosRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): EmpleadosFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecIngreso: currentTime,
      fecNac: currentTime,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertEmpleadosRawValueToEmpleados(rawEmpleados: EmpleadosFormRawValue | NewEmpleadosFormRawValue): IEmpleados | NewEmpleados {
    return {
      ...rawEmpleados,
      fecIngreso: dayjs(rawEmpleados.fecIngreso, DATE_TIME_FORMAT),
      fecNac: dayjs(rawEmpleados.fecNac, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawEmpleados.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawEmpleados.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertEmpleadosToEmpleadosRawValue(
    empleados: IEmpleados | (Partial<NewEmpleados> & EmpleadosFormDefaults)
  ): EmpleadosFormRawValue | PartialWithRequiredKeyOf<NewEmpleadosFormRawValue> {
    return {
      ...empleados,
      fecIngreso: empleados.fecIngreso ? empleados.fecIngreso.format(DATE_TIME_FORMAT) : undefined,
      fecNac: empleados.fecNac ? empleados.fecNac.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: empleados.fecCrea ? empleados.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: empleados.fecModif ? empleados.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
