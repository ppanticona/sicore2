import { ISecuencias, NewSecuencias } from './secuencias.model';

export const sampleWithRequiredData: ISecuencias = {
  id: '29625c13-1552-454a-a0c6-ce58a4efb8e5',
};

export const sampleWithPartialData: ISecuencias = {
  id: 'a4193699-4933-4b54-8012-15e7104095e1',
  descripcion: 'Checking Diseñador Iran',
  sequence: 43015,
};

export const sampleWithFullData: ISecuencias = {
  id: 'c8faed11-3833-463c-b684-c0c4a49c93aa',
  seqid: 'Increible Morado',
  descripcion: 'Salchichas TCP Colonia',
  sequence: 65987,
};

export const sampleWithNewData: NewSecuencias = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
