export interface ISecuencias {
  id: string;
  seqid?: string | null;
  descripcion?: string | null;
  sequence?: number | null;
}

export type NewSecuencias = Omit<ISecuencias, 'id'> & { id: null };
