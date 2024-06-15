import dayjs from 'dayjs/esm';

export interface IOrden {
  id: string;
  numOrden?: number | null;
  observacion?: string | null;
  fecIngreso?: dayjs.Dayjs | null;
  fecSalida?: dayjs.Dayjs | null;
  codCanal?: string | null;
  tipOrden?: string | null;
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

export type NewOrden = Omit<IOrden, 'id'> & { id: null };
