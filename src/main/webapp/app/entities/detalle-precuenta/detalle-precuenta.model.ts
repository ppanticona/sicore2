import dayjs from 'dayjs/esm';
import { IOrden } from 'app/entities/orden/orden.model';
import { IAutorizacion } from 'app/entities/autorizacion/autorizacion.model';
import { IRegVenta } from 'app/entities/reg-venta/reg-venta.model';
import { IPrecuenta } from 'app/entities/precuenta/precuenta.model';
import { IDetalleOrden } from 'app/entities/detalle-orden/detalle-orden.model';

export interface IDetallePrecuenta {
  id: string;
  correlativo?: number | null;
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
  autorizacion?: Pick<IAutorizacion, 'id'> | null;
  regVenta?: Pick<IRegVenta, 'id'> | null;
  precuenta?: Pick<IPrecuenta, 'id'> | null;
  detalleOrden?: Pick<IDetalleOrden, 'id'> | null;
}

export type NewDetallePrecuenta = Omit<IDetallePrecuenta, 'id'> & { id: null };
