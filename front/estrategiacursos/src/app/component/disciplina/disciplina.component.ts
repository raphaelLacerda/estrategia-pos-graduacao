import { ProfessorService } from './../../service/professor.service';
import { DisciplinaService } from './../../service/disciplina.service';
import { Disciplina } from './../../model/disciplina.model';
import { Component, OnInit } from '@angular/core';
import { ComponentBaseCadastroDirective } from '../component-base-cadastro';
import { Professor } from 'src/app/model/professor.model';

@Component({
  selector: 'app-disciplina',
  templateUrl: './disciplina.component.html',
  styleUrls: ['./disciplina.component.css'],
})
export class DisciplinaComponent extends ComponentBaseCadastroDirective<Disciplina> {
  professores: Professor[] = [];

  async ngOnInit() {
    super.ngOnInit();
    if (this.professores.length == 0) {
      this.professores = await this.injector.get(ProfessorService).listar();
    }
  }

  getService(): DisciplinaService {
    return this.injector.get(DisciplinaService);
  }
  novaInstancia(): Disciplina {
    return new Disciplina();
  }
}
