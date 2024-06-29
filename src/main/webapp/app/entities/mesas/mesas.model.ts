import dayjs from 'dayjs/esm';
import { IOrden } from 'app/entities/orden/orden.model';
import { ISede } from 'app/entities/sede/sede.model';
import { IUser } from 'app/entities/user/user.model';

export interface IMesas {
  id: string;
  seqMesa?: number | null;
  nroMesa?: number | null;
  codGrupo?: string | null;
  categoria?: string | null;
  capacidad?: number | null;
  indMesaJunta?: boolean | null;
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
  sede?: Pick<ISede, 'id'> | null;
  user?: Pick<IUser, 'id'> | null;
}

export type NewMesas = Omit<IMesas, 'id'> & { id: null };
