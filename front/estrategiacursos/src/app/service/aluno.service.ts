import { constants } from './constants';
import { environment } from '../../environments/environment';
import { Usuario } from '../model/usuario.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { Aluno } from '../model/aluno.model';
import { ServiceBase } from './service-base.service';

@Injectable({
  providedIn: 'root',
})
export class AlunoService extends ServiceBase<Aluno> {
  getURL(): string {
    return constants.ALUNOS_API;
  }
}
