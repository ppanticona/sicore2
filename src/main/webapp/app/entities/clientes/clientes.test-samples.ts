import dayjs from 'dayjs/esm';

import { IClientes, NewClientes } from './clientes.model';

export const sampleWithRequiredData: IClientes = {
  id: 'ed9d3986-6102-4075-8112-2aa7e64891a7',
  tipDoc: 'AI synergize',
  nroDoc: 'embrace',
  nombres: 'engage Adelante',
  apellidos: 'infrastructures Gris productize',
  fecNac: dayjs('2024-06-13T04:51'),
  estado: 'invoice microchip Berkshire',
  version: 17338,
  indDel: true,
  fecCrea: dayjs('2024-06-13T02:36'),
  usuCrea: 'SMTP enable Account',
  ipCrea: 'Gran array Ejecutivo',
};

export const sampleWithPartialData: IClientes = {
  id: '6ac89d88-4eee-4316-a9b3-8b52acef6b5e',
  tipDoc: 'Configurable Representante',
  nroDoc: 'Account Trinidad Gris',
  nombres: 'invoice next-generation input',
  apellidos: 'wireless',
  categoria: 'Hormigon one-to-one',
  tel1: 'Increible',
  tel2: 'Pequeño digital',
  direccion: 'Horizontal Forint',
  refDirecc: 'Intercambiable innovative navigate',
  distrito: 'Enfocado Mobilidad encriptar',
  fecNac: dayjs('2024-06-13T06:51'),
  userId: 'Huerta eyeballs',
  estado: 'Arroyo',
  version: 44484,
  indDel: false,
  fecCrea: dayjs('2024-06-12T23:46'),
  usuCrea: 'Metal extranet action-items',
  ipCrea: 'Gris',
  usuModif: 'synthesizing synthesizing',
};

export const sampleWithFullData: IClientes = {
  id: 'fb6fac85-65b6-4729-bb58-aa41e93db4de',
  tipDoc: 'program Práctico',
  nroDoc: 'drive',
  nombres: 'e-business Verde',
  apellidos: 'de estrategia',
  categoria: 'Rojo La',
  tel1: 'empower',
  tel2: 'Madera',
  correo: 'magnetic Configurable SMS',
  direccion: '24/7',
  refDirecc: 'Música Comunicaciones Increible',
  distrito: 'JSON',
  fecNac: dayjs('2024-06-12T21:11'),
  userId: 'UAE En',
  estado: 'Buckinghamshire',
  version: 7748,
  indDel: true,
  fecCrea: dayjs('2024-06-12T21:30'),
  usuCrea: 'Asistente',
  ipCrea: 'leverage',
  fecModif: dayjs('2024-06-13T00:24'),
  usuModif: 'alarm',
  ipModif: 'sensor payment',
};

export const sampleWithNewData: NewClientes = {
  tipDoc: 'implementación Productor capacidad',
  nroDoc: 'Interacciones Equilibrado Canarias',
  nombres: 'withdrawal convergence',
  apellidos: 'Dinánmico optical',
  fecNac: dayjs('2024-06-12T21:39'),
  estado: 'Honduras',
  version: 13828,
  indDel: false,
  fecCrea: dayjs('2024-06-12T21:52'),
  usuCrea: 'Account Moda Berkshire',
  ipCrea: 'B2B deposit iterate',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
