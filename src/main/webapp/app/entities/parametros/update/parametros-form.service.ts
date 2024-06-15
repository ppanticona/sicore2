import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IParametros, NewParametros } from '../parametros.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IParametros for edit and NewParametrosFormGroupInput for create.
 */
type ParametrosFormGroupInput = IParametros | PartialWithRequiredKeyOf<NewParametros>;

type ParametrosFormDefaults = Pick<NewParametros, 'id'>;

type ParametrosFormGroupContent = {
  id: FormControl<IParametros['id'] | NewParametros['id']>;
  codParam: FormControl<IParametros['codParam']>;
  detParam: FormControl<IParametros['detParam']>;
  primParam: FormControl<IParametros['primParam']>;
  segParam: FormControl<IParametros['segParam']>;
  tercParam: FormControl<IParametros['tercParam']>;
  cuarParam: FormControl<IParametros['cuarParam']>;
  descripcion: FormControl<IParametros['descripcion']>;
  sequence: FormControl<IParametros['sequence']>;
};

export type ParametrosFormGroup = FormGroup<ParametrosFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ParametrosFormService {
  createParametrosFormGroup(parametros: ParametrosFormGroupInput = { id: null }): ParametrosFormGroup {
    const parametrosRawValue = {
      ...this.getFormDefaults(),
      ...parametros,
    };
    return new FormGroup<ParametrosFormGroupContent>({
      id: new FormControl(
        { value: parametrosRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      codParam: new FormControl(parametrosRawValue.codParam),
      detParam: new FormControl(parametrosRawValue.detParam),
      primParam: new FormControl(parametrosRawValue.primParam),
      segParam: new FormControl(parametrosRawValue.segParam),
      tercParam: new FormControl(parametrosRawValue.tercParam),
      cuarParam: new FormControl(parametrosRawValue.cuarParam),
      descripcion: new FormControl(parametrosRawValue.descripcion),
      sequence: new FormControl(parametrosRawValue.sequence),
    });
  }

  getParametros(form: ParametrosFormGroup): IParametros | NewParametros {
    return form.getRawValue() as IParametros | NewParametros;
  }

  resetForm(form: ParametrosFormGroup, parametros: ParametrosFormGroupInput): void {
    const parametrosRawValue = { ...this.getFormDefaults(), ...parametros };
    form.reset(
      {
        ...parametrosRawValue,
        id: { value: parametrosRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ParametrosFormDefaults {
    return {
      id: null,
    };
  }
}
