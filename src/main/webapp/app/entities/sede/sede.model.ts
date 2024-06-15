import dayjs from 'dayjs/esm';

export interface ISede {
  id: string;
  codSede?: number | null;
  descripcion?: number | null;
  categoria?: string | null;
  tel1?: string | null;
  tel2?: string | null;
  correo?: string | null;
  direccion?: string | null;
  refDirecc?: string | null;
  distrito?: string | null;
  fecAper?: dayjs.Dayjs | null;
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

export type NewSede = Omit<ISede, 'id'> & { id: null };
