import dayjs from 'dayjs/esm';

import { IMesas, NewMesas } from './mesas.model';

export const sampleWithRequiredData: IMesas = {
  id: '882f02b9-19d4-4a88-a340-a1b4a67854e2',
  seqMesa: 17186,
  nroMesa: 45290,
  indMesaJunta: false,
  estado: 'open-source Calle Liechtenstein',
  version: 45405,
  indDel: false,
  fecCrea: dayjs('2024-06-12T17:50'),
  usuCrea: 'Supervisor redundant',
  ipCrea: 'Queso invoice copying',
};

export const sampleWithPartialData: IMesas = {
  id: 'ebbdfe21-cd0d-4e9e-88bb-6e5528a06f8b',
  seqMesa: 41411,
  nroMesa: 32251,
  indMesaJunta: false,
  estado: 'Cambridgeshire',
  version: 19616,
  indDel: false,
  fecCrea: dayjs('2024-06-13T01:04'),
  usuCrea: 'arquitectura Sabroso',
  ipCrea: 'syndicate PCI',
  ipModif: 'Bedfordshire',
};

export const sampleWithFullData: IMesas = {
  id: '97b2d0ee-2dd1-422d-9c7d-2b57b380787b',
  seqMesa: 28732,
  nroMesa: 40263,
  codGrupo: 'Director multitarea Atún',
  categoria: 'Cuentas invoice',
  capacidad: 80528,
  indMesaJunta: false,
  estado: 'cero',
  version: 61803,
  indDel: true,
  fecCrea: dayjs('2024-06-13T08:14'),
  usuCrea: 'EXE',
  ipCrea: 'bluetooth',
  fecModif: dayjs('2024-06-13T03:42'),
  usuModif: 'proactive',
  ipModif: 'Cuentas',
};

export const sampleWithNewData: NewMesas = {
  seqMesa: 78427,
  nroMesa: 18445,
  indMesaJunta: true,
  estado: 'e-commerce tiempo copy',
  version: 19518,
  indDel: true,
  fecCrea: dayjs('2024-06-13T08:41'),
  usuCrea: 'generate',
  ipCrea: 'escalable Kip partnerships',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
