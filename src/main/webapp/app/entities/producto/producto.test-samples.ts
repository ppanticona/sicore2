import dayjs from 'dayjs/esm';

import { IProducto, NewProducto } from './producto.model';

export const sampleWithRequiredData: IProducto = {
  id: '8a4deb02-683d-4854-8a8a-9eeee169f3b8',
  descripcion: 'Increible Pequeño',
  estado: 'Madera',
  version: 63528,
  indDel: false,
  fecCrea: dayjs('2024-06-13T07:49'),
  usuCrea: 'Azul',
  ipCrea: 'Puerta',
};

export const sampleWithPartialData: IProducto = {
  id: '4fc6d5f1-2b72-40f2-b312-cef7f1b63f24',
  codProducto: 'Refinado Sopa input',
  codBarra: 'Hormigon Negro',
  descripcion: 'wireless',
  categoria: 'iterate Amarillo didactica',
  costoProd: 84954,
  estado: 'intermedia Patatas',
  version: 72138,
  indDel: true,
  fecCrea: dayjs('2024-06-13T09:45'),
  usuCrea: 'microchip',
  ipCrea: 'firewall communities',
  ipModif: 'Soporte optimize Morado',
};

export const sampleWithFullData: IProducto = {
  id: '2a4373aa-3e89-442d-940e-b0ed9e58a310',
  codProducto: 'Loan Acero',
  tipProducto: 'Cuentas Música Avon',
  codQr: 'Cliente cross-platform withdrawal',
  codBarra: 'parsing Krona',
  descripcion: 'Baleares',
  detalleDesc: 'estandardización bypassing',
  valor: 74040,
  categoria: 'deposit Bricolaje',
  subCategoria: 'Factores',
  categoriaMenu: 'generate Account Borders',
  urlImage: 'PCI',
  fecIniVig: dayjs('2024-06-12T20:59'),
  fecFinVig: dayjs('2024-06-13T03:17'),
  costoProd: 48675,
  estado: 'Buckinghamshire',
  version: 25904,
  indDel: true,
  fecCrea: dayjs('2024-06-12T20:27'),
  usuCrea: 'Account',
  ipCrea: 'Gris',
  fecModif: dayjs('2024-06-13T03:22'),
  usuModif: 'Interacciones',
  ipModif: 'Colon PNG',
};

export const sampleWithNewData: NewProducto = {
  descripcion: 'Ladrillo',
  estado: 'potenciada Buckinghamshire',
  version: 76125,
  indDel: true,
  fecCrea: dayjs('2024-06-13T16:42'),
  usuCrea: 'Account',
  ipCrea: 'JBOD Vasco',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
