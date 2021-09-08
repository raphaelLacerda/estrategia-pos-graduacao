import { Disciplina } from './disciplina.model';
import { ModelBase } from './model-base.model';
export class Concurso implements ModelBase {
  id: number;
  nome: string;
  diaDoLancamento: Date;
  dataDaProva: Date;
  disciplinas: Disciplina[] = [];
  valor: number;
  valorBruto: number;

}
