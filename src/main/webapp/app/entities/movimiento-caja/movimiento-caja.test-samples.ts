import dayjs from 'dayjs/esm';

import { IMovimientoCaja, NewMovimientoCaja } from './movimiento-caja.model';

export const sampleWithRequiredData: IMovimientoCaja = {
  id: '3cce3652-b4e4-40f1-93bd-86285189551e',
  version: 72879,
  indDel: true,
  fecCrea: dayjs('2024-06-13T03:58'),
  usuCrea: 'Madera Loan models',
  ipCrea: 'logística',
};

export const sampleWithPartialData: IMovimientoCaja = {
  id: '07a35201-0b99-48c9-879a-3d2aa236aba2',
  tipMovimiento: 'Bajada',
  monto: 68821,
  formPago: '24 Guantes',
  comprobante: 'Japón Mesa hack',
  version: 94700,
  indDel: false,
  fecCrea: dayjs('2024-06-12T17:52'),
  usuCrea: 'Savings Manzana Granito',
  ipCrea: 'invoice mediante Berkshire',
  ipModif: 'blockchains Gorro Corporativo',
};

export const sampleWithFullData: IMovimientoCaja = {
  id: 'c3d6e8b1-f2bc-4fe9-8648-b7c22750e099',
  tipMovimiento: 'Teclado',
  concepto: 'Bajada back-end',
  monto: 13799,
  tipMoneda: 'Camiseta',
  formPago: 'Salud',
  comprobante: 'invoice Práctico',
  fecMovimiento: dayjs('2024-06-13T00:22'),
  version: 33001,
  indDel: false,
  fecCrea: dayjs('2024-06-12T17:28'),
  usuCrea: 'state Parafarmacia THX',
  ipCrea: 'Guantes monitor',
  fecModif: dayjs('2024-06-13T02:19'),
  usuModif: 'Rial Madera Inteligente',
  ipModif: 'program Buckinghamshire Guapa',
};

export const sampleWithNewData: NewMovimientoCaja = {
  version: 51809,
  indDel: true,
  fecCrea: dayjs('2024-06-12T21:55'),
  usuCrea: 'Central',
  ipCrea: 'Pollo synthesize',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
