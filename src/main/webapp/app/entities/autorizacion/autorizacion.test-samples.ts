import dayjs from 'dayjs/esm';

import { IAutorizacion, NewAutorizacion } from './autorizacion.model';

export const sampleWithRequiredData: IAutorizacion = {
  id: '2d03d54c-a734-411a-a447-56d68dc11956',
  tipAutorizacion: 'indexing Camiseta basado',
  subTipAutorizacion: 'recíproca Guantes',
  token: 'Global Amarillo Azul',
  fecIniVig: dayjs('2024-06-12T22:41'),
  fecFinVig: dayjs('2024-06-12T22:04'),
  estado: 'override auxiliary Sector',
  version: 31816,
  indDel: true,
  fecCrea: dayjs('2024-06-12T22:25'),
  usuCrea: 'Ladrillo efficient',
  ipCrea: 'Negro',
};

export const sampleWithPartialData: IAutorizacion = {
  id: '5e526785-0688-4e57-b269-6394dc56b0c8',
  tipAutorizacion: "Guapo Pa'anga hack",
  subTipAutorizacion: 'Groenlandia de strategic',
  concepto: 'deposit experiences Namibia',
  comentario: 'compelling',
  token: 'Libyan blockchains users',
  fecIniVig: dayjs('2024-06-13T01:30'),
  fecFinVig: dayjs('2024-06-12T23:31'),
  estado: 'sistemática',
  version: 1450,
  indDel: true,
  fecCrea: dayjs('2024-06-13T04:20'),
  usuCrea: 'reintermediate Clonado compress',
  ipCrea: 'empower',
  fecModif: dayjs('2024-06-13T14:39'),
  ipModif: 'Humano Artesanal',
};

export const sampleWithFullData: IAutorizacion = {
  id: '434c90e0-1aa4-4318-a448-5183c5f83a06',
  tipAutorizacion: 'wireless',
  subTipAutorizacion: 'Jefe',
  concepto: 'Berkshire',
  comentario: 'Zimbabwe port Bicicleta',
  monto: 30879,
  moneda: 'SAS',
  token: 'Galicia innovate',
  fecIniVig: dayjs('2024-06-13T17:13'),
  fecFinVig: dayjs('2024-06-12T17:33'),
  estado: 'Clonado sinergia Expandido',
  version: 44040,
  indDel: false,
  fecCrea: dayjs('2024-06-13T08:15'),
  usuCrea: 'supply-chains Camerún',
  ipCrea: 'Amarillo Genérico',
  fecModif: dayjs('2024-06-13T08:04'),
  usuModif: 'Calidad Gris',
  ipModif: 'e-tailers driver Diseñador',
};

export const sampleWithNewData: NewAutorizacion = {
  tipAutorizacion: 'International RAM SAS',
  subTipAutorizacion: 'Genérico Castilla-La',
  token: 'Card Librería proporciona',
  fecIniVig: dayjs('2024-06-13T12:41'),
  fecFinVig: dayjs('2024-06-13T02:25'),
  estado: 'Creativo',
  version: 77036,
  indDel: true,
  fecCrea: dayjs('2024-06-13T06:47'),
  usuCrea: 'fases',
  ipCrea: 'Camiseta Optimizado generating',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
