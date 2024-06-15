import dayjs from 'dayjs/esm';

export interface IEmpleados {
  id: string;
  tipDoc?: string | null;
  nroDoc?: string | null;
  nombres?: string | null;
  apellidos?: string | null;
  categoria?: string | null;
  tel1?: string | null;
  tel2?: string | null;
  correo?: string | null;
  direccion?: string | null;
  refDirecc?: string | null;
  distrito?: string | null;
  fecIngreso?: dayjs.Dayjs | null;
  fecNac?: dayjs.Dayjs | null;
  imagen?: string | null;
  userId?: string | null;
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

export type NewEmpleados = Omit<IEmpleados, 'id'> & { id: null };
