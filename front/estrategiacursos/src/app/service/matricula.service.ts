import { constants } from './constants';
import { environment } from '../../environments/environment';
import { Usuario } from '../model/usuario.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { Aluno } from '../model/aluno.model';
import { ServiceBase } from './service-base.service';
import { Disciplina } from '../model/disciplina.model';
import { Matricula } from '../model/matricula.model';

@Injectable({
  providedIn: 'root',
})
export class MatriculaService extends ServiceBase<Matricula> {
  getURL(): string {
    return constants.MATRICULAS_API;
  }
}
