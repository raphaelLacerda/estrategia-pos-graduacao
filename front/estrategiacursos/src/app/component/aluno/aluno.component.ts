import { AlunoService } from './../../service/aluno.service';
import { Component, OnInit } from '@angular/core';
import { Aluno } from 'src/app/model/aluno.model';
import { ServiceBase } from 'src/app/service/service-base.service';
import { ComponentBaseCadastroDirective } from '../component-base-cadastro';

@Component({
  selector: 'app-aluno',
  templateUrl: './aluno.component.html',
  styleUrls: ['./aluno.component.css']
})
export class AlunoComponent extends ComponentBaseCadastroDirective<Aluno>{
  getService(): AlunoService {
    return this.injector.get(AlunoService);
  }
  novaInstancia(): Aluno {
    return new Aluno();
  }


}
