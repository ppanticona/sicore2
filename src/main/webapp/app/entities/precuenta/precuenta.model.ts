import dayjs from 'dayjs/esm';
import { IOrden } from 'app/entities/orden/orden.model';

export interface IPrecuenta {
  id: string;
  numPrecuenta?: number | null;
  observacion?: string | null;
  estado?: string | null;
  version?: number | null;
  indDel?: boolean | null;
  fecCrea?: dayjs.Dayjs | null;
  usuCrea?: string | null;
  ipCrea?: string | null;
  fecModif?: dayjs.Dayjs | null;
  usuModif?: string | null;
  ipModif?: string | null;
  orden?: Pick<IOrden, 'id'> | null;
}

export type NewPrecuenta = Omit<IPrecuenta, 'id'> & { id: null };
