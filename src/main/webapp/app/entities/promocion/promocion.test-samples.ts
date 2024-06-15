import dayjs from 'dayjs/esm';

import { IPromocion, NewPromocion } from './promocion.model';

export const sampleWithRequiredData: IPromocion = {
  id: '216c3569-75e6-4bec-891a-f0aaa2bade0e',
  tipPromocion: 'synthesize monitor',
  fecIniVig: dayjs('2024-06-12T17:44'),
  fecFinVig: dayjs('2024-06-13T03:05'),
  comentarios: '../fake-data/blob/hipster.txt',
  estado: 'Azul',
  version: 20700,
  indDel: false,
  fecCrea: dayjs('2024-06-12T21:27'),
  usuCrea: 'primary digital',
  ipCrea: 'Checking',
};

export const sampleWithPartialData: IPromocion = {
  id: 'af4780c1-2c2f-4bac-a763-df071cbdda95',
  tipPromocion: 'payment',
  val2: 'Rial',
  val3: 'Directo Orientado',
  val4: 'Bricolaje Soluciones',
  fecIniVig: dayjs('2024-06-13T06:54'),
  fecFinVig: dayjs('2024-06-13T15:51'),
  comentarios: '../fake-data/blob/hipster.txt',
  estado: 'granular Monetary Extendido',
  version: 48829,
  indDel: false,
  fecCrea: dayjs('2024-06-12T23:02'),
  usuCrea: 'terciaria',
  ipCrea: 'Plástico Pre-emptivo',
  fecModif: dayjs('2024-06-12T17:52'),
  usuModif: 'Account transparent',
};

export const sampleWithFullData: IPromocion = {
  id: '28a95100-f890-48dc-97fe-7e4112d138b8',
  tipPromocion: 'Metal',
  val1: 'synthesize local Azul',
  val2: 'Inteligente',
  val3: 'Asistente digital',
  val4: 'Cantabria interfaces Bebes',
  val5: 'Zelanda por',
  maxProm: 'extend Seguridada enfoque',
  fecIniVig: dayjs('2024-06-13T07:53'),
  fecFinVig: dayjs('2024-06-13T03:44'),
  comentarios: '../fake-data/blob/hipster.txt',
  estado: 'RSS Rambla Kip',
  version: 90250,
  indDel: true,
  fecCrea: dayjs('2024-06-13T13:13'),
  usuCrea: 'Account',
  ipCrea: 'Bacon',
  fecModif: dayjs('2024-06-13T01:29'),
  usuModif: 'Soluciones',
  ipModif: 'Asistente metódica',
};

export const sampleWithNewData: NewPromocion = {
  tipPromocion: 'compressing deposit Intuitivo',
  fecIniVig: dayjs('2024-06-12T22:36'),
  fecFinVig: dayjs('2024-06-12T17:43'),
  comentarios: '../fake-data/blob/hipster.txt',
  estado: 'Identidad Toallas',
  version: 54662,
  indDel: false,
  fecCrea: dayjs('2024-06-13T14:58'),
  usuCrea: 'vertical International',
  ipCrea: 'transmitting 4th Rojo',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
