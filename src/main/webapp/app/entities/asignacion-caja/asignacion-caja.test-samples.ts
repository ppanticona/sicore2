import dayjs from 'dayjs/esm';

import { IAsignacionCaja, NewAsignacionCaja } from './asignacion-caja.model';

export const sampleWithRequiredData: IAsignacionCaja = {
  id: '1eb7f233-5892-47ea-85a6-035f10bf546c',
  codAsignacion: 'Ensalada Decoración Asturias',
  estado: 'éxito Asociado productividad',
  version: 56204,
  indDel: true,
  fecCrea: dayjs('2024-06-13T15:45'),
  usuCrea: 'redundant Asistente Integración',
  ipCrea: 'Borders Refinado',
};

export const sampleWithPartialData: IAsignacionCaja = {
  id: '1c890fcf-f8c6-4b99-9bc2-9477768ca1cb',
  codAsignacion: 'Andalucía',
  montoMaximoSoles: 38406,
  diferenciaSoles: 62161,
  mntoInicialDolares: 19831,
  mntoFinalDolares: 65227,
  montoMaximoDolares: 96757,
  observacionCierre: 'solid Salchichas',
  estado: 'Seguro Uruguayo',
  version: 15305,
  indDel: true,
  fecCrea: dayjs('2024-06-13T13:22'),
  usuCrea: 'Account Azul Central',
  ipCrea: 'actitud online',
  fecModif: dayjs('2024-06-13T00:15'),
  ipModif: 'regional Ladrillo Buckinghamshire',
};

export const sampleWithFullData: IAsignacionCaja = {
  id: '296733cb-b124-472e-a78f-42ba1f4db3df',
  codAsignacion: 'Ladrillo Patatas Atún',
  mntoInicialSoles: 17982,
  mntoFinalSoles: 4464,
  montoMaximoSoles: 44421,
  diferenciaSoles: 56487,
  mntoInicialDolares: 15336,
  mntoFinalDolares: 40117,
  montoMaximoDolares: 35372,
  diferenciaDolares: 70369,
  observacionApertura: 'visionary',
  observacionCierre: 'SCSI deposit Refinado',
  fecAsignacion: dayjs('2024-06-12T17:48'),
  estado: 'Verde Checking',
  version: 82416,
  indDel: false,
  fecCrea: dayjs('2024-06-12T23:24'),
  usuCrea: 'implement Infraestructura regional',
  ipCrea: 'Principado Ergonómico Implementación',
  fecModif: dayjs('2024-06-13T13:50'),
  usuModif: 'Joyería Loan Pantalones',
  ipModif: 'Luxemburgo Senior interface',
};

export const sampleWithNewData: NewAsignacionCaja = {
  codAsignacion: 'Poblado sistémica Cambridgeshire',
  estado: 'Interacciones Martinica',
  version: 45824,
  indDel: false,
  fecCrea: dayjs('2024-06-12T22:33'),
  usuCrea: 'invoice',
  ipCrea: 'desafío Productor copying',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
