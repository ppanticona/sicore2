import dayjs from 'dayjs/esm';
import { IProducto } from 'app/entities/producto/producto.model';

export interface IAutorizacion {
  id: string;
  tipAutorizacion?: string | null;
  subTipAutorizacion?: string | null;
  concepto?: string | null;
  comentario?: string | null;
  monto?: number | null;
  moneda?: string | null;
  token?: string | null;
  fecIniVig?: dayjs.Dayjs | null;
  fecFinVig?: dayjs.Dayjs | null;
  estado?: string | null;
  version?: number | null;
  indDel?: boolean | null;
  fecCrea?: dayjs.Dayjs | null;
  usuCrea?: string | null;
  ipCrea?: string | null;
  fecModif?: dayjs.Dayjs | null;
  usuModif?: string | null;
  ipModif?: string | null;
  producto?: Pick<IProducto, 'id'> | null;
}

export type NewAutorizacion = Omit<IAutorizacion, 'id'> & { id: null };
