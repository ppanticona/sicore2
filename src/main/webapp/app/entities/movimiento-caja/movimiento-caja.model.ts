import dayjs from 'dayjs/esm';
import { IAsignacionCaja } from 'app/entities/asignacion-caja/asignacion-caja.model';
import { IAutorizacion } from 'app/entities/autorizacion/autorizacion.model';

export interface IMovimientoCaja {
  id: string;
  tipMovimiento?: string | null;
  concepto?: string | null;
  monto?: number | null;
  tipMoneda?: string | null;
  formPago?: string | null;
  comprobante?: string | null;
  fecMovimiento?: dayjs.Dayjs | null;
  version?: number | null;
  indDel?: boolean | null;
  fecCrea?: dayjs.Dayjs | null;
  usuCrea?: string | null;
  ipCrea?: string | null;
  fecModif?: dayjs.Dayjs | null;
  usuModif?: string | null;
  ipModif?: string | null;
  asignacionCaja?: Pick<IAsignacionCaja, 'id'> | null;
  autorizacion?: Pick<IAutorizacion, 'id'> | null;
}

export type NewMovimientoCaja = Omit<IMovimientoCaja, 'id'> & { id: null };
