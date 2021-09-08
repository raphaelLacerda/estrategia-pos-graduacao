import { ModelBase } from './model-base.model';
import { Professor } from './professor.model';
export class Disciplina implements ModelBase {
  id: number;
  nome: string;
  preco: number;
  professor: Professor;
}
