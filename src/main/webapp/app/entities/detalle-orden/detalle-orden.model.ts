import dayjs from 'dayjs/esm';
import { IOrden } from 'app/entities/orden/orden.model';
import { IPromocion } from 'app/entities/promocion/promocion.model';
import { IAutorizacion } from 'app/entities/autorizacion/autorizacion.model';
import { IProducto } from 'app/entities/producto/producto.model';

export interface IDetalleOrden {
  id: string;
  observacion?: string | null;
  monto?: number | null;
  indPagado?: boolean | null;
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
  promocion?: Pick<IPromocion, 'id'> | null;
  autorizacion?: Pick<IAutorizacion, 'id'> | null;
  producto?: Pick<IProducto, 'id'> | null;
}

export type NewDetalleOrden = Omit<IDetalleOrden, 'id'> & { id: null };
