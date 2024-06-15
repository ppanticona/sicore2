import dayjs from 'dayjs/esm';

import { IOrden, NewOrden } from './orden.model';

export const sampleWithRequiredData: IOrden = {
  id: '5eeb0487-dac9-471d-a3f1-b04125ab76a5',
  numOrden: 90699,
  codCanal: 'País',
  tipOrden: 'en Etiopía Extremadura',
  estado: 'Pelota Estados',
  version: 84724,
  indDel: true,
  fecCrea: dayjs('2024-06-12T21:48'),
  usuCrea: 'Diseñador digital Loan',
  ipCrea: 'Ejecutivo',
};

export const sampleWithPartialData: IOrden = {
  id: 'cb5cd81f-d62f-4e61-8828-85c9383f6612',
  numOrden: 97391,
  fecSalida: dayjs('2024-06-12T23:24'),
  codCanal: 'Nepal Sol',
  tipOrden: 'Increible',
  estado: 'Reducido Cliente Granito',
  version: 11251,
  indDel: true,
  fecCrea: dayjs('2024-06-13T05:41'),
  usuCrea: 'digital',
  ipCrea: 'Inteligente',
  ipModif: 'Jefe Bahamas',
};

export const sampleWithFullData: IOrden = {
  id: '52843ad6-a007-4de4-a21f-0f2d6dd9c051',
  numOrden: 71230,
  observacion: '../fake-data/blob/hipster.txt',
  fecIngreso: dayjs('2024-06-13T03:32'),
  fecSalida: dayjs('2024-06-13T05:16'),
  codCanal: 'unleash',
  tipOrden: 'International',
  estado: 'analizada Paseo Opcional',
  version: 48503,
  indDel: true,
  fecCrea: dayjs('2024-06-13T05:08'),
  usuCrea: 'paradigms Plástico fritas',
  ipCrea: 'reboot Andalucía bifurcada',
  fecModif: dayjs('2024-06-13T16:37'),
  usuModif: 'Account Orgánico 24/365',
  ipModif: 'compress',
};

export const sampleWithNewData: NewOrden = {
  numOrden: 21725,
  codCanal: 'Account Heredado',
  tipOrden: 'program Marca Shilling',
  estado: 'Genérico Castilla',
  version: 19014,
  indDel: false,
  fecCrea: dayjs('2024-06-13T05:34'),
  usuCrea: 'Coche input Inteligente',
  ipCrea: 'SAS scalable middleware',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
