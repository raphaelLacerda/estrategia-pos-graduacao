import { ConcursoService } from './../../service/concursos.service';
import { Component, OnInit } from '@angular/core';
import { Concurso } from 'src/app/model/concurso.model';
import { ComponentBaseCadastroDirective } from '../component-base-cadastro';

@Component({
  selector: 'app-concurso',
  templateUrl: './concurso.component.html',
  styleUrls: ['./concurso.component.css']
})
export class ConcursoComponent extends ComponentBaseCadastroDirective<Concurso> {


  getService(): ConcursoService {
    return this.injector.get(ConcursoService);
  }
  novaInstancia(): Concurso {
    return new Concurso();
  }
  editar(c: Concurso) {
    super.editar(c);
    this.objeto.dataDaProva = new Date(this.objeto.dataDaProva);
  }
}
