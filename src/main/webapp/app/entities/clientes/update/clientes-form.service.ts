import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IClientes, NewClientes } from '../clientes.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IClientes for edit and NewClientesFormGroupInput for create.
 */
type ClientesFormGroupInput = IClientes | PartialWithRequiredKeyOf<NewClientes>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IClientes | NewClientes> = Omit<T, 'fecNac' | 'fecCrea' | 'fecModif'> & {
  fecNac?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type ClientesFormRawValue = FormValueOf<IClientes>;

type NewClientesFormRawValue = FormValueOf<NewClientes>;

type ClientesFormDefaults = Pick<NewClientes, 'id' | 'fecNac' | 'indDel' | 'fecCrea' | 'fecModif'>;

type ClientesFormGroupContent = {
  id: FormControl<ClientesFormRawValue['id'] | NewClientes['id']>;
  tipDoc: FormControl<ClientesFormRawValue['tipDoc']>;
  nroDoc: FormControl<ClientesFormRawValue['nroDoc']>;
  nombres: FormControl<ClientesFormRawValue['nombres']>;
  apellidos: FormControl<ClientesFormRawValue['apellidos']>;
  categoria: FormControl<ClientesFormRawValue['categoria']>;
  tel1: FormControl<ClientesFormRawValue['tel1']>;
  tel2: FormControl<ClientesFormRawValue['tel2']>;
  correo: FormControl<ClientesFormRawValue['correo']>;
  direccion: FormControl<ClientesFormRawValue['direccion']>;
  refDirecc: FormControl<ClientesFormRawValue['refDirecc']>;
  distrito: FormControl<ClientesFormRawValue['distrito']>;
  fecNac: FormControl<ClientesFormRawValue['fecNac']>;
  userId: FormControl<ClientesFormRawValue['userId']>;
  estado: FormControl<ClientesFormRawValue['estado']>;
  version: FormControl<ClientesFormRawValue['version']>;
  indDel: FormControl<ClientesFormRawValue['indDel']>;
  fecCrea: FormControl<ClientesFormRawValue['fecCrea']>;
  usuCrea: FormControl<ClientesFormRawValue['usuCrea']>;
  ipCrea: FormControl<ClientesFormRawValue['ipCrea']>;
  fecModif: FormControl<ClientesFormRawValue['fecModif']>;
  usuModif: FormControl<ClientesFormRawValue['usuModif']>;
  ipModif: FormControl<ClientesFormRawValue['ipModif']>;
};

export type ClientesFormGroup = FormGroup<ClientesFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ClientesFormService {
  createClientesFormGroup(clientes: ClientesFormGroupInput = { id: null }): ClientesFormGroup {
    const clientesRawValue = this.convertClientesToClientesRawValue({
      ...this.getFormDefaults(),
      ...clientes,
    });
    return new FormGroup<ClientesFormGroupContent>({
      id: new FormControl(
        { value: clientesRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      tipDoc: new FormControl(clientesRawValue.tipDoc, {
        validators: [Validators.required],
      }),
      nroDoc: new FormControl(clientesRawValue.nroDoc, {
        validators: [Validators.required],
      }),
      nombres: new FormControl(clientesRawValue.nombres, {
        validators: [Validators.required],
      }),
      apellidos: new FormControl(clientesRawValue.apellidos, {
        validators: [Validators.required],
      }),
      categoria: new FormControl(clientesRawValue.categoria),
      tel1: new FormControl(clientesRawValue.tel1),
      tel2: new FormControl(clientesRawValue.tel2),
      correo: new FormControl(clientesRawValue.correo),
      direccion: new FormControl(clientesRawValue.direccion),
      refDirecc: new FormControl(clientesRawValue.refDirecc),
      distrito: new FormControl(clientesRawValue.distrito),
      fecNac: new FormControl(clientesRawValue.fecNac, {
        validators: [Validators.required],
      }),
      userId: new FormControl(clientesRawValue.userId),
      estado: new FormControl(clientesRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(clientesRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(clientesRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(clientesRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(clientesRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(clientesRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(clientesRawValue.fecModif),
      usuModif: new FormControl(clientesRawValue.usuModif),
      ipModif: new FormControl(clientesRawValue.ipModif),
    });
  }

  getClientes(form: ClientesFormGroup): IClientes | NewClientes {
    return this.convertClientesRawValueToClientes(form.getRawValue() as ClientesFormRawValue | NewClientesFormRawValue);
  }

  resetForm(form: ClientesFormGroup, clientes: ClientesFormGroupInput): void {
    const clientesRawValue = this.convertClientesToClientesRawValue({ ...this.getFormDefaults(), ...clientes });
    form.reset(
      {
        ...clientesRawValue,
        id: { value: clientesRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ClientesFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecNac: currentTime,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertClientesRawValueToClientes(rawClientes: ClientesFormRawValue | NewClientesFormRawValue): IClientes | NewClientes {
    return {
      ...rawClientes,
      fecNac: dayjs(rawClientes.fecNac, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawClientes.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawClientes.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertClientesToClientesRawValue(
    clientes: IClientes | (Partial<NewClientes> & ClientesFormDefaults)
  ): ClientesFormRawValue | PartialWithRequiredKeyOf<NewClientesFormRawValue> {
    return {
      ...clientes,
      fecNac: clientes.fecNac ? clientes.fecNac.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: clientes.fecCrea ? clientes.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: clientes.fecModif ? clientes.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
