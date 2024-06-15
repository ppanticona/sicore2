import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IProveedores, NewProveedores } from '../proveedores.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IProveedores for edit and NewProveedoresFormGroupInput for create.
 */
type ProveedoresFormGroupInput = IProveedores | PartialWithRequiredKeyOf<NewProveedores>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IProveedores | NewProveedores> = Omit<T, 'fecNac' | 'fecCrea' | 'fecModif'> & {
  fecNac?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type ProveedoresFormRawValue = FormValueOf<IProveedores>;

type NewProveedoresFormRawValue = FormValueOf<NewProveedores>;

type ProveedoresFormDefaults = Pick<NewProveedores, 'id' | 'fecNac' | 'indDel' | 'fecCrea' | 'fecModif'>;

type ProveedoresFormGroupContent = {
  id: FormControl<ProveedoresFormRawValue['id'] | NewProveedores['id']>;
  tipDoc: FormControl<ProveedoresFormRawValue['tipDoc']>;
  nroDoc: FormControl<ProveedoresFormRawValue['nroDoc']>;
  nombres: FormControl<ProveedoresFormRawValue['nombres']>;
  apellidos: FormControl<ProveedoresFormRawValue['apellidos']>;
  categoria: FormControl<ProveedoresFormRawValue['categoria']>;
  tel1: FormControl<ProveedoresFormRawValue['tel1']>;
  tel2: FormControl<ProveedoresFormRawValue['tel2']>;
  correo: FormControl<ProveedoresFormRawValue['correo']>;
  direccion: FormControl<ProveedoresFormRawValue['direccion']>;
  refDirecc: FormControl<ProveedoresFormRawValue['refDirecc']>;
  distrito: FormControl<ProveedoresFormRawValue['distrito']>;
  fecNac: FormControl<ProveedoresFormRawValue['fecNac']>;
  userId: FormControl<ProveedoresFormRawValue['userId']>;
  estado: FormControl<ProveedoresFormRawValue['estado']>;
  version: FormControl<ProveedoresFormRawValue['version']>;
  indDel: FormControl<ProveedoresFormRawValue['indDel']>;
  fecCrea: FormControl<ProveedoresFormRawValue['fecCrea']>;
  usuCrea: FormControl<ProveedoresFormRawValue['usuCrea']>;
  ipCrea: FormControl<ProveedoresFormRawValue['ipCrea']>;
  fecModif: FormControl<ProveedoresFormRawValue['fecModif']>;
  usuModif: FormControl<ProveedoresFormRawValue['usuModif']>;
  ipModif: FormControl<ProveedoresFormRawValue['ipModif']>;
};

export type ProveedoresFormGroup = FormGroup<ProveedoresFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ProveedoresFormService {
  createProveedoresFormGroup(proveedores: ProveedoresFormGroupInput = { id: null }): ProveedoresFormGroup {
    const proveedoresRawValue = this.convertProveedoresToProveedoresRawValue({
      ...this.getFormDefaults(),
      ...proveedores,
    });
    return new FormGroup<ProveedoresFormGroupContent>({
      id: new FormControl(
        { value: proveedoresRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      tipDoc: new FormControl(proveedoresRawValue.tipDoc, {
        validators: [Validators.required],
      }),
      nroDoc: new FormControl(proveedoresRawValue.nroDoc, {
        validators: [Validators.required],
      }),
      nombres: new FormControl(proveedoresRawValue.nombres, {
        validators: [Validators.required],
      }),
      apellidos: new FormControl(proveedoresRawValue.apellidos, {
        validators: [Validators.required],
      }),
      categoria: new FormControl(proveedoresRawValue.categoria),
      tel1: new FormControl(proveedoresRawValue.tel1),
      tel2: new FormControl(proveedoresRawValue.tel2),
      correo: new FormControl(proveedoresRawValue.correo),
      direccion: new FormControl(proveedoresRawValue.direccion),
      refDirecc: new FormControl(proveedoresRawValue.refDirecc),
      distrito: new FormControl(proveedoresRawValue.distrito),
      fecNac: new FormControl(proveedoresRawValue.fecNac, {
        validators: [Validators.required],
      }),
      userId: new FormControl(proveedoresRawValue.userId),
      estado: new FormControl(proveedoresRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(proveedoresRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(proveedoresRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(proveedoresRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(proveedoresRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(proveedoresRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(proveedoresRawValue.fecModif),
      usuModif: new FormControl(proveedoresRawValue.usuModif),
      ipModif: new FormControl(proveedoresRawValue.ipModif),
    });
  }

  getProveedores(form: ProveedoresFormGroup): IProveedores | NewProveedores {
    return this.convertProveedoresRawValueToProveedores(form.getRawValue() as ProveedoresFormRawValue | NewProveedoresFormRawValue);
  }

  resetForm(form: ProveedoresFormGroup, proveedores: ProveedoresFormGroupInput): void {
    const proveedoresRawValue = this.convertProveedoresToProveedoresRawValue({ ...this.getFormDefaults(), ...proveedores });
    form.reset(
      {
        ...proveedoresRawValue,
        id: { value: proveedoresRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ProveedoresFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecNac: currentTime,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertProveedoresRawValueToProveedores(
    rawProveedores: ProveedoresFormRawValue | NewProveedoresFormRawValue
  ): IProveedores | NewProveedores {
    return {
      ...rawProveedores,
      fecNac: dayjs(rawProveedores.fecNac, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawProveedores.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawProveedores.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertProveedoresToProveedoresRawValue(
    proveedores: IProveedores | (Partial<NewProveedores> & ProveedoresFormDefaults)
  ): ProveedoresFormRawValue | PartialWithRequiredKeyOf<NewProveedoresFormRawValue> {
    return {
      ...proveedores,
      fecNac: proveedores.fecNac ? proveedores.fecNac.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: proveedores.fecCrea ? proveedores.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: proveedores.fecModif ? proveedores.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
