import dayjs from 'dayjs/esm';
import { IUser } from 'app/entities/user/user.model';

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
  user?: Pick<IUser, 'id'> | null;
}

export type NewAsistencia = Omit<IAsistencia, 'id'> & { id: null };
