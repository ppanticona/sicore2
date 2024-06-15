import dayjs from 'dayjs/esm';

export interface ICaja {
  id: string;
  tipCaja?: string | null;
  descripcion?: string | null;
  estado?: string | null;
  version?: number | null;
  indDel?: boolean | null;
  fecCrea?: dayjs.Dayjs | null;
  usuCrea?: string | null;
  ipCrea?: string | null;
  fecModif?: dayjs.Dayjs | null;
  usuModif?: string | null;
  ipModif?: string | null;
}

export type NewCaja = Omit<ICaja, 'id'> & { id: null };
