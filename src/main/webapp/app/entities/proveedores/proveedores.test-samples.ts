import dayjs from 'dayjs/esm';

import { IProveedores, NewProveedores } from './proveedores.model';

export const sampleWithRequiredData: IProveedores = {
  id: 'a84f622a-2af9-451e-badc-48b343b2416e',
  tipDoc: 'EXE Ruanda 1080p',
  nroDoc: 'Asistente',
  nombres: 'Acero',
  apellidos: 'invoice',
  fecNac: dayjs('2024-06-13T15:01'),
  estado: 'harness orquestar',
  version: 73888,
  indDel: false,
  fecCrea: dayjs('2024-06-12T18:00'),
  usuCrea: 'Guapa mindshare',
  ipCrea: 'tolerancia Práctico Castilla-La',
};

export const sampleWithPartialData: IProveedores = {
  id: '68b9531c-175b-4bed-a9d5-b36646552ef2',
  tipDoc: 'functionalities previsión',
  nroDoc: 'circuit deposit',
  nombres: 'Digitalizado',
  apellidos: 'Bulgaria Account',
  tel2: 'matrix',
  correo: 'Card program',
  refDirecc: 'Brazilian deposit mobile',
  fecNac: dayjs('2024-06-12T18:51'),
  userId: 'Zapatos Silla',
  estado: 'Planificador',
  version: 40936,
  indDel: true,
  fecCrea: dayjs('2024-06-12T18:32'),
  usuCrea: 'partnerships Ordenador deliver',
  ipCrea: 'Marketing Optimización',
  fecModif: dayjs('2024-06-12T23:22'),
  ipModif: 'holística',
};

export const sampleWithFullData: IProveedores = {
  id: 'ce942cac-c981-4c0c-81cf-e8f9906cd7f1',
  tipDoc: 'Open-source matrix Oficial',
  nroDoc: 'synthesizing Bajada',
  nombres: 'Facilitador Factores wireless',
  apellidos: 'Guantes action-items',
  categoria: 'Obligatorio',
  tel1: 'fritas País',
  tel2: 'Haiti',
  correo: 'Violeta emulación Guapo',
  direccion: 'enable',
  refDirecc: 'calculate',
  distrito: 'tangible Refinado digital',
  fecNac: dayjs('2024-06-12T22:03'),
  userId: 'unleash Madera dynamic',
  estado: 'Burundi',
  version: 80563,
  indDel: false,
  fecCrea: dayjs('2024-06-12T21:00'),
  usuCrea: 'pixel el transmit',
  ipCrea: 'Marca',
  fecModif: dayjs('2024-06-12T18:27'),
  usuModif: 'Credit',
  ipModif: 'Pizza',
};

export const sampleWithNewData: NewProveedores = {
  tipDoc: 'Programa Plástico',
  nroDoc: 'Hecho e-enable hack',
  nombres: 'copying servidor',
  apellidos: 'nueva Algodón',
  fecNac: dayjs('2024-06-13T13:06'),
  estado: 'Videojuegos Decoración',
  version: 83094,
  indDel: true,
  fecCrea: dayjs('2024-06-13T01:06'),
  usuCrea: 'Joyería Operaciones primary',
  ipCrea: 'metódica invoice backing',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
