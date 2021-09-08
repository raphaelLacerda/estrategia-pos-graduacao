import { Matricula } from './matricula.model';
import { ModelBase } from './model-base.model';
export class Aluno implements ModelBase {
  id: number;
  nome: string;
  email: string;
  matriculas: Matricula[] = [];
}
