import dayjs from 'dayjs/esm';

import { IPrecuenta, NewPrecuenta } from './precuenta.model';

export const sampleWithRequiredData: IPrecuenta = {
  id: '089c6e5f-bf33-40db-9477-54359a11136d',
  numPrecuenta: 93681,
  estado: 'Implementación Lempira RSS',
  version: 6968,
  indDel: false,
  fecCrea: dayjs('2024-06-13T06:58'),
  usuCrea: 'Manzana Guapa',
  ipCrea: 'Cine wireless',
};

export const sampleWithPartialData: IPrecuenta = {
  id: '618a6f84-a2d1-4315-ac61-89f96cb59ee3',
  numPrecuenta: 48757,
  observacion: '../fake-data/blob/hipster.txt',
  estado: 'Respuesta Rioja',
  version: 95254,
  indDel: false,
  fecCrea: dayjs('2024-06-12T18:47'),
  usuCrea: 'payment Representante',
  ipCrea: 'maximize Borders',
  fecModif: dayjs('2024-06-12T17:18'),
  usuModif: 'mindshare',
};

export const sampleWithFullData: IPrecuenta = {
  id: 'a0ce268a-6826-4ec4-b696-4f2118e75b52',
  numPrecuenta: 47477,
  observacion: '../fake-data/blob/hipster.txt',
  estado: 'Cliente Belarussian',
  version: 63219,
  indDel: false,
  fecCrea: dayjs('2024-06-12T19:47'),
  usuCrea: 'Galicia bifurcada Ramal',
  ipCrea: 'Inteligente engineer',
  fecModif: dayjs('2024-06-12T21:45'),
  usuModif: 'uniforme architect Granito',
  ipModif: 'Opcional Moda',
};

export const sampleWithNewData: NewPrecuenta = {
  numPrecuenta: 46568,
  estado: 'Buckinghamshire invoice',
  version: 42008,
  indDel: true,
  fecCrea: dayjs('2024-06-13T11:53'),
  usuCrea: 'Librería',
  ipCrea: 'Relacciones Hormigon',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
