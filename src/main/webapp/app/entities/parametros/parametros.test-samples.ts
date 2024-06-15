import { IParametros, NewParametros } from './parametros.model';

export const sampleWithRequiredData: IParametros = {
  id: '0f921432-45ce-410a-aa3c-3c9e9835d2d1',
};

export const sampleWithPartialData: IParametros = {
  id: '9e058ef3-9798-41d2-91df-ec4243d930e0',
  primParam: 'Guapa Account',
  segParam: 'Negro',
  tercParam: 'initiatives Toallas',
  descripcion: 'La',
  sequence: 62400,
};

export const sampleWithFullData: IParametros = {
  id: 'b18a3070-aad9-4a61-bbe4-cfabddf615c9',
  codParam: 'virtual la',
  detParam: 'navigating Bebes',
  primParam: 'Cambridgeshire',
  segParam: 'Intranet',
  tercParam: 'nivel',
  cuarParam: 'Bahraini',
  descripcion: 'harness',
  sequence: 27742,
};

export const sampleWithNewData: NewParametros = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
