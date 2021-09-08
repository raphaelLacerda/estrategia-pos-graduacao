import { environment } from "src/environments/environment";

const apiUrl = environment.SERVICO_ESTRATEGIA + '/api/v1';

export const constants = {
  ALUNOS_API: apiUrl + '/alunos',
  DISCIPLINAS_API: apiUrl + '/disciplinas',
  CONCURSOS_API: apiUrl + '/concursos',
  PROFESSORES_API: apiUrl + '/professores',
  MATRICULAS_API: apiUrl + '/matriculas',

};
