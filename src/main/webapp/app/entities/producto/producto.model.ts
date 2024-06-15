import dayjs from 'dayjs/esm';

export interface IProducto {
  id: string;
  codProducto?: string | null;
  tipProducto?: string | null;
  codQr?: string | null;
  codBarra?: string | null;
  descripcion?: string | null;
  detalleDesc?: string | null;
  valor?: number | null;
  categoria?: string | null;
  subCategoria?: string | null;
  categoriaMenu?: string | null;
  urlImage?: string | null;
  fecIniVig?: dayjs.Dayjs | null;
  fecFinVig?: dayjs.Dayjs | null;
  costoProd?: number | null;
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

export type NewProducto = Omit<IProducto, 'id'> & { id: null };
