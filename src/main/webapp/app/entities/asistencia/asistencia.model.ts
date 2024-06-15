import dayjs from 'dayjs/esm';
import { IEmpleados } from 'app/entities/empleados/empleados.model';

export interface IAsistencia {
  id: string;
  tipAsistente?: string | null;
  resultado?: string | null;
  anoAsistencia?: number | null;
  mesAsistencia?: number | null;
  diaAsistencia?: number | null;
  observacion?: string | null;
  comentarios?: string | null;
  estado?: string | null;
  version?: number | null;
  indDel?: boolean | null;
  fecCrea?: dayjs.Dayjs | null;
  usuCrea?: string | null;
  ipCrea?: string | null;
  fecModif?: dayjs.Dayjs | null;
  usuModif?: string | null;
  ipModif?: string | null;
  userId?: Pick<IEmpleados, 'id'> | null;
}

export type NewAsistencia = Omit<IAsistencia, 'id'> & { id: null };
