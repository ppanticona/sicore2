import dayjs from 'dayjs/esm';

import { ICaja, NewCaja } from './caja.model';

export const sampleWithRequiredData: ICaja = {
  id: 'c9391671-95b5-4570-b506-47d97850f450',
  tipCaja: 'parsing Ladrillo Distrito',
  descripcion: 'Organizado virtual indexing',
  estado: 'Sudanese',
  version: 19917,
  indDel: true,
  fecCrea: dayjs('2024-06-13T05:36'),
  usuCrea: 'infrastructures brand Coordinador',
  ipCrea: 'program XML Interno',
};

export const sampleWithPartialData: ICaja = {
  id: '1259dce7-62ff-41ff-b4c0-5ae530e3d37d',
  tipCaja: 'CSS',
  descripcion: 'target Andalucía Money',
  estado: 'Salchichas software',
  version: 94874,
  indDel: false,
  fecCrea: dayjs('2024-06-13T16:42'),
  usuCrea: 'Refinado Extramuros',
  ipCrea: 'state',
  fecModif: dayjs('2024-06-13T01:56'),
  usuModif: 'implement',
};

export const sampleWithFullData: ICaja = {
  id: '7ea431dd-ab89-4924-9895-9bce6b613841',
  tipCaja: 'fases quantify Gris',
  descripcion: 'Andalucía global',
  estado: 'users Gerente',
  version: 2037,
  indDel: true,
  fecCrea: dayjs('2024-06-12T22:47'),
  usuCrea: 'Diseñador programming',
  ipCrea: 'Usabilidad terciaria',
  fecModif: dayjs('2024-06-12T23:16'),
  usuModif: 'system Guantes',
  ipModif: 'Intercambiable Ladrillo',
};

export const sampleWithNewData: NewCaja = {
  tipCaja: 'TCP Borders',
  descripcion: 'Cliente Productor',
  estado: 'monetize Granito',
  version: 31301,
  indDel: true,
  fecCrea: dayjs('2024-06-13T00:45'),
  usuCrea: 'Buckinghamshire Comodos open-source',
  ipCrea: 'Dinánmico Money monetize',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
