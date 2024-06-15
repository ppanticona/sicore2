import dayjs from 'dayjs/esm';

import { IAsistencia, NewAsistencia } from './asistencia.model';

export const sampleWithRequiredData: IAsistencia = {
  id: 'aa291c57-c64d-4bb8-8f61-6d4f5293e27b',
  tipAsistente: 'Navarra',
  comentarios: '../fake-data/blob/hipster.txt',
  estado: 'Informática Monitorizado',
  version: 43931,
  indDel: false,
  fecCrea: dayjs('2024-06-13T03:56'),
  usuCrea: 'Monte robust',
  ipCrea: 'Increible',
};

export const sampleWithPartialData: IAsistencia = {
  id: 'ff4e0e91-b2ff-42b2-8eda-489ccad45cba',
  tipAsistente: 'Investment Director',
  resultado: 'Pescado Baleares',
  anoAsistencia: 88044,
  mesAsistencia: 21742,
  diaAsistencia: 2606,
  comentarios: '../fake-data/blob/hipster.txt',
  estado: 'program mano killer',
  version: 9356,
  indDel: false,
  fecCrea: dayjs('2024-06-13T09:36'),
  usuCrea: '6th',
  ipCrea: 'Borders e-business',
  usuModif: 'Mascotas Distrito indexing',
  ipModif: 'Amarillo Austria',
};

export const sampleWithFullData: IAsistencia = {
  id: '31ae396c-05a3-4f37-b057-84b21dc4c476',
  tipAsistente: 'Metal',
  resultado: 'Práctico',
  anoAsistencia: 3654,
  mesAsistencia: 81041,
  diaAsistencia: 6107,
  observacion: 'capacitor Artesanal',
  comentarios: '../fake-data/blob/hipster.txt',
  estado: 'producto Investment',
  version: 3942,
  indDel: true,
  fecCrea: dayjs('2024-06-13T10:31'),
  usuCrea: 'models',
  ipCrea: 'orquestar metodologías Blanco',
  fecModif: dayjs('2024-06-13T07:06'),
  usuModif: 'paradigms backing Madera',
  ipModif: 'Creativo Decoración',
};

export const sampleWithNewData: NewAsistencia = {
  tipAsistente: 'Rioja Pelota',
  comentarios: '../fake-data/blob/hipster.txt',
  estado: 'Heredado Distrito',
  version: 58855,
  indDel: false,
  fecCrea: dayjs('2024-06-13T17:14'),
  usuCrea: 'PNG overriding Cantabria',
  ipCrea: 'transparent mano',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
