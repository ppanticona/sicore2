import dayjs from 'dayjs/esm';

import { IDetalleOrden, NewDetalleOrden } from './detalle-orden.model';

export const sampleWithRequiredData: IDetalleOrden = {
  id: '11f5516c-c8e3-49ad-944b-dd8415bec834',
  estado: 'redundant Inteligente Aldea',
  version: 69193,
  indDel: true,
  fecCrea: dayjs('2024-06-13T04:55'),
  usuCrea: 'override Especialista Gourde',
  ipCrea: 'Ladrillo Gris Algodón',
};

export const sampleWithPartialData: IDetalleOrden = {
  id: '9364c91a-8815-42bd-9a31-e841993a678e',
  observacion: '../fake-data/blob/hipster.txt',
  monto: 27797,
  indPagado: true,
  estado: 'León Chile Silver',
  version: 28754,
  indDel: false,
  fecCrea: dayjs('2024-06-13T14:36'),
  usuCrea: 'SCSI Identidad Creativo',
  ipCrea: 'payment dot-com',
  fecModif: dayjs('2024-06-12T18:48'),
  usuModif: 'SMS Especialista',
  ipModif: 'Omani parse',
};

export const sampleWithFullData: IDetalleOrden = {
  id: '5e782ced-82ad-49b1-9266-1def55c9d1fa',
  observacion: '../fake-data/blob/hipster.txt',
  monto: 83089,
  indPagado: false,
  estado: 'payment connect',
  version: 46372,
  indDel: true,
  fecCrea: dayjs('2024-06-12T23:42'),
  usuCrea: 'Central Berkshire Madera',
  ipCrea: 'paradigms Versatil',
  fecModif: dayjs('2024-06-13T04:30'),
  usuModif: 'Sabroso',
  ipModif: 'Azul Morado',
};

export const sampleWithNewData: NewDetalleOrden = {
  estado: 'override methodologies',
  version: 30418,
  indDel: true,
  fecCrea: dayjs('2024-06-12T17:19'),
  usuCrea: 'Belice Marca',
  ipCrea: 'Blanco portals',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
