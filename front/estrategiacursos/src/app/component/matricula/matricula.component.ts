import { AlunoService } from './../../service/aluno.service';
import { Aluno } from './../../model/aluno.model';
import { MatriculaService } from './../../service/matricula.service';
import { ConcursoService } from './../../service/concursos.service';
import { Component, OnInit } from '@angular/core';
import { Concurso } from 'src/app/model/concurso.model';
import { Matricula } from 'src/app/model/matricula.model';
import { ComponentBaseCadastroDirective } from '../component-base-cadastro';

@Component({
  selector: 'app-matricula',
  templateUrl: './matricula.component.html',
  styleUrls: ['./matricula.component.css'],
})
export class MatriculaComponent extends ComponentBaseCadastroDirective<Matricula> {
  concursos: Concurso[] = [];
  alunos: Aluno[] = [];

  async ngOnInit() {
    super.ngOnInit();
    if (this.concursos.length == 0) {
      this.concursos = await this.injector
        .get(ConcursoService)
        .listarDisponiveis();
    }
    if (this.alunos.length == 0) {
      this.alunos = await this.injector.get(AlunoService).listar();
    }
  }

  getService(): MatriculaService {
    return this.injector.get(MatriculaService);
  }
  novaInstancia(): Matricula {
    return new Matricula();
  }
}
