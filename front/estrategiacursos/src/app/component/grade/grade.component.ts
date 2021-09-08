import { DisciplinaService } from './../../service/disciplina.service';
import { ConcursoService } from './../../service/concursos.service';
import { Component, OnInit } from '@angular/core';
import { Concurso } from 'src/app/model/concurso.model';
import { Disciplina } from 'src/app/model/disciplina.model';

@Component({
  selector: 'app-grade',
  templateUrl: './grade.component.html',
  styleUrls: ['./grade.component.css'],
})
export class GradeComponent implements OnInit {
  concursos: Concurso[] = [];
  concursoSelecionado: Concurso;
  disciplinaSelecionada: Disciplina;
  disciplinas: Disciplina[] = [];
  disciplinasOriginais: Disciplina[] = [];

  constructor(
    private concursoService: ConcursoService,
    private disciplinaService: DisciplinaService
  ) {}

  async ngOnInit() {
    this.concursos = await this.concursoService.listar();
    this.disciplinas = await this.disciplinaService.listar();
    this.disciplinasOriginais = this.disciplinas;
  }

  atualizarConcurso() {
    this.concursoService.salvar(this.concursoSelecionado);
  }

  filtrarDisciplicasDoConcursoSelecionado() {

    this.disciplinas = this.disciplinasOriginais
      .filter(d => !this.concursoSelecionado.disciplinas.map(disc => disc.id).includes(d.id));

  }
}
