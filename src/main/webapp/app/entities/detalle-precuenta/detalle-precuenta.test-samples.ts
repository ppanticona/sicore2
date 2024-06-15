import dayjs from 'dayjs/esm';

import { IDetallePrecuenta, NewDetallePrecuenta } from './detalle-precuenta.model';

export const sampleWithRequiredData: IDetallePrecuenta = {
  id: '83ed710b-0f5d-4802-8395-a12f033ee1cc',
  correlativo: 4940,
  estado: 'Pizza Integración calculate',
  version: 91447,
  indDel: true,
  fecCrea: dayjs('2024-06-13T04:57'),
  usuCrea: 'Avanzado calculating Corporativo',
  ipCrea: 'reboot Teclado transition',
};

export const sampleWithPartialData: IDetallePrecuenta = {
  id: '9540f7e7-d70b-48e5-9d7a-833fb9fb2478',
  correlativo: 63457,
  estado: 'generate estable',
  version: 2567,
  indDel: false,
  fecCrea: dayjs('2024-06-13T04:48'),
  usuCrea: 'compress',
  ipCrea: 'capacidad Money',
  usuModif: 'Progresivo Pantalones cero',
  ipModif: 'transition synergize Nepal',
};

export const sampleWithFullData: IDetallePrecuenta = {
  id: '7b029a0e-0ed2-4dfe-8b07-8b1eb174c60a',
  correlativo: 60317,
  estado: 'bus Buckinghamshire Granito',
  version: 29453,
  indDel: true,
  fecCrea: dayjs('2024-06-13T11:37'),
  usuCrea: 'Tajikistan',
  ipCrea: 'wireless',
  fecModif: dayjs('2024-06-13T04:26'),
  usuModif: 'Negro',
  ipModif: 'Toallas wireless',
};

export const sampleWithNewData: NewDetallePrecuenta = {
  correlativo: 72805,
  estado: 'port Comunicaciones real-time',
  version: 51862,
  indDel: false,
  fecCrea: dayjs('2024-06-13T07:35'),
  usuCrea: 'fritas explícita hardware',
  ipCrea: 'Leona Fantástico',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
