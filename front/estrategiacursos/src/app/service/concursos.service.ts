import { constants } from './constants';
import { environment } from '../../environments/environment';
import { Usuario } from '../model/usuario.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { Aluno } from '../model/aluno.model';
import { ServiceBase } from './service-base.service';
import { Disciplina } from '../model/disciplina.model';
import { Professor } from '../model/professor.model';
import { Concurso } from '../model/concurso.model';

@Injectable({
  providedIn: 'root',
})
export class ConcursoService extends ServiceBase<Concurso> {
  getURL(): string {
    return constants.CONCURSOS_API;
  }

  listarDisponiveis(): Promise<Concurso[]> {
    return this.http.get<Concurso[]>(this.getURL()+'/disponiveis').toPromise();
  }
}
