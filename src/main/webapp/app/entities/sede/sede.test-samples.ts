import dayjs from 'dayjs/esm';

import { ISede, NewSede } from './sede.model';

export const sampleWithRequiredData: ISede = {
  id: '45facd13-462f-487b-95a3-b4aa843bc44d',
  codSede: 3321,
  descripcion: 31250,
  fecAper: dayjs('2024-06-13T07:29'),
  estado: 'compressing deposit',
  version: 46470,
  indDel: true,
  fecCrea: dayjs('2024-06-12T23:44'),
  usuCrea: 'Oficial Jefe Acero',
  ipCrea: 'Funcionalidad',
};

export const sampleWithPartialData: ISede = {
  id: 'd69172b6-0ddc-456d-b150-1e504897a3f2',
  codSede: 27823,
  descripcion: 51243,
  tel1: 'y Algodón',
  fecAper: dayjs('2024-06-13T13:20'),
  estado: 'Salud Ngultrum',
  version: 62989,
  indDel: true,
  fecCrea: dayjs('2024-06-13T11:38'),
  usuCrea: 'a Algodón Luxemburgo',
  ipCrea: 'Account SMTP',
  ipModif: 'Pula Cataluña multitarea',
};

export const sampleWithFullData: ISede = {
  id: '626518a3-2f8c-4c0c-8564-12e02347ad34',
  codSede: 46639,
  descripcion: 61897,
  categoria: 'deploy Barrio Pelota',
  tel1: 'withdrawal',
  tel2: 'Galicia generación Teclado',
  correo: 'array Pelota Heredado',
  direccion: 'JSON Pollo',
  refDirecc: 'transmit',
  distrito: 'Rambla primary Ergonómico',
  fecAper: dayjs('2024-06-12T21:47'),
  estado: 'Normas up Rua',
  version: 70798,
  indDel: false,
  fecCrea: dayjs('2024-06-13T06:36'),
  usuCrea: 'Mascotas Bricolaje generate',
  ipCrea: 'Account Ingeniero transmit',
  fecModif: dayjs('2024-06-12T20:04'),
  usuModif: 'programming Jardines Morado',
  ipModif: 'e-business XML Cantabria',
};

export const sampleWithNewData: NewSede = {
  codSede: 83885,
  descripcion: 83736,
  fecAper: dayjs('2024-06-12T23:52'),
  estado: 'Librería web-enabled Negro',
  version: 59843,
  indDel: true,
  fecCrea: dayjs('2024-06-13T13:02'),
  usuCrea: 'cultivate Parafarmacia Vía',
  ipCrea: 'haptic',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
