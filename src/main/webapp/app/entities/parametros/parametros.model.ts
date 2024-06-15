export interface IParametros {
  id: string;
  codParam?: string | null;
  detParam?: string | null;
  primParam?: string | null;
  segParam?: string | null;
  tercParam?: string | null;
  cuarParam?: string | null;
  descripcion?: string | null;
  sequence?: number | null;
}

export type NewParametros = Omit<IParametros, 'id'> & { id: null };
