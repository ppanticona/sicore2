import dayjs from 'dayjs/esm';

import { IEmpleados, NewEmpleados } from './empleados.model';

export const sampleWithRequiredData: IEmpleados = {
  id: '01c2d355-28c7-4cf8-bb8e-4cafc0ca914b',
  tipDoc: 'Marketing Ladrillo',
  nroDoc: 'Programable enhance',
  nombres: 'colaboración Ringgit SAS',
  apellidos: 'paradigma Ucrania',
  fecNac: dayjs('2024-06-12T22:06'),
  estado: 'Salchichas Mancha',
  version: 14587,
  indDel: false,
  fecCrea: dayjs('2024-06-13T07:47'),
  usuCrea: 'primary distributed Bicicleta',
  ipCrea: 'groupware Account Deportes',
};

export const sampleWithPartialData: IEmpleados = {
  id: 'b9794521-9670-4d63-88c4-008b6ea6b7f2',
  tipDoc: 'hibrida Práctico Salida',
  nroDoc: 'Mesa Bicicleta',
  nombres: 'moderador cultivate',
  apellidos: 'port',
  categoria: 'Violeta Arroyo Camiseta',
  refDirecc: 'Acero',
  distrito: 'Operativo Borders',
  fecIngreso: dayjs('2024-06-12T17:33'),
  fecNac: dayjs('2024-06-13T15:04'),
  imagen: 'auxiliary',
  userId: 'digital',
  estado: 'Galicia optimizada',
  version: 86805,
  indDel: false,
  fecCrea: dayjs('2024-06-13T01:25'),
  usuCrea: 'index Franc',
  ipCrea: 'BCEAO',
  usuModif: 'systems',
  ipModif: 'Asimilado navigate deposit',
};

export const sampleWithFullData: IEmpleados = {
  id: '33aaa92b-fe75-4f15-a371-d02159743f94',
  tipDoc: 'Juguetería',
  nroDoc: 'index',
  nombres: 'Bricolaje',
  apellidos: 'Hormigon parse',
  categoria: 'Bricolaje',
  tel1: 'abierto',
  tel2: 'cohesiva invoice Plástico',
  correo: 'Inversor',
  direccion: 'Moda front-end',
  refDirecc: 'architectures',
  distrito: 'SQL Savings payment',
  fecIngreso: dayjs('2024-06-12T20:12'),
  fecNac: dayjs('2024-06-13T10:12'),
  imagen: 'Apartamento Borders Virtual',
  userId: 'Ferrocarril archivo Inteligente',
  estado: 'Tailandia Gris',
  version: 7086,
  indDel: true,
  fecCrea: dayjs('2024-06-12T23:52'),
  usuCrea: 'Rústico',
  ipCrea: 'syndicate Morado Amarillo',
  fecModif: dayjs('2024-06-13T03:12'),
  usuModif: 'Irlanda Adelante',
  ipModif: 'Datos Amarillo Toallas',
};

export const sampleWithNewData: NewEmpleados = {
  tipDoc: 'payment',
  nroDoc: 'solución',
  nombres: 'Bajada Account',
  apellidos: 'Prolongación Metal',
  fecNac: dayjs('2024-06-13T13:16'),
  estado: 'Account interactive',
  version: 22667,
  indDel: true,
  fecCrea: dayjs('2024-06-13T11:18'),
  usuCrea: 'Librería',
  ipCrea: 'deposit EXE',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
