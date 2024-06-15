import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ISecuencias, NewSecuencias } from '../secuencias.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISecuencias for edit and NewSecuenciasFormGroupInput for create.
 */
type SecuenciasFormGroupInput = ISecuencias | PartialWithRequiredKeyOf<NewSecuencias>;

type SecuenciasFormDefaults = Pick<NewSecuencias, 'id'>;

type SecuenciasFormGroupContent = {
  id: FormControl<ISecuencias['id'] | NewSecuencias['id']>;
  seqid: FormControl<ISecuencias['seqid']>;
  descripcion: FormControl<ISecuencias['descripcion']>;
  sequence: FormControl<ISecuencias['sequence']>;
};

export type SecuenciasFormGroup = FormGroup<SecuenciasFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SecuenciasFormService {
  createSecuenciasFormGroup(secuencias: SecuenciasFormGroupInput = { id: null }): SecuenciasFormGroup {
    const secuenciasRawValue = {
      ...this.getFormDefaults(),
      ...secuencias,
    };
    return new FormGroup<SecuenciasFormGroupContent>({
      id: new FormControl(
        { value: secuenciasRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      seqid: new FormControl(secuenciasRawValue.seqid),
      descripcion: new FormControl(secuenciasRawValue.descripcion),
      sequence: new FormControl(secuenciasRawValue.sequence),
    });
  }

  getSecuencias(form: SecuenciasFormGroup): ISecuencias | NewSecuencias {
    return form.getRawValue() as ISecuencias | NewSecuencias;
  }

  resetForm(form: SecuenciasFormGroup, secuencias: SecuenciasFormGroupInput): void {
    const secuenciasRawValue = { ...this.getFormDefaults(), ...secuencias };
    form.reset(
      {
        ...secuenciasRawValue,
        id: { value: secuenciasRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SecuenciasFormDefaults {
    return {
      id: null,
    };
  }
}
