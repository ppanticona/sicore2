import dayjs from 'dayjs/esm';
import { IUser } from 'app/entities/user/user.model';
import { ICaja } from 'app/entities/caja/caja.model';

export interface IAsignacionCaja {
  id: string;
  codAsignacion?: string | null;
  mntoInicialSoles?: number | null;
  mntoFinalSoles?: number | null;
  montoMaximoSoles?: number | null;
  diferenciaSoles?: number | null;
  mntoInicialDolares?: number | null;
  mntoFinalDolares?: number | null;
  montoMaximoDolares?: number | null;
  diferenciaDolares?: number | null;
  observacionApertura?: string | null;
  observacionCierre?: string | null;
  fecAsignacion?: dayjs.Dayjs | null;
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
  caja?: Pick<ICaja, 'id'> | null;
}

export type NewAsignacionCaja = Omit<IAsignacionCaja, 'id'> & { id: null };
