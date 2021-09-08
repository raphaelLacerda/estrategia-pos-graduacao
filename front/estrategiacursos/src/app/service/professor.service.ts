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

@Injectable({
  providedIn: 'root',
})
export class ProfessorService extends ServiceBase<Professor> {
  getURL(): string {
    return constants.PROFESSORES_API;
  }
}
