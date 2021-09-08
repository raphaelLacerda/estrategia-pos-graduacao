import { Aluno } from './aluno.model';
import { Concurso } from './concurso.model';
import { ModelBase } from './model-base.model';
export class Matricula implements ModelBase {
  id: number;
  aluno: Aluno;
  concurso: Concurso;
  avaliacao: number;
  dataEfetivacao: Date;
  acessoPermitido: boolean;
}
