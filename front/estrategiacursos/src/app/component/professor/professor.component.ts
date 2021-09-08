import { Component, OnInit } from '@angular/core';
import { Professor } from 'src/app/model/professor.model';
import { ProfessorService } from 'src/app/service/professor.service';
import { ComponentBaseCadastroDirective } from '../component-base-cadastro';

@Component({
  selector: 'app-professor',
  templateUrl: './professor.component.html',
  styleUrls: ['./professor.component.css']
})
export class ProfessorComponent extends ComponentBaseCadastroDirective<Professor>{
  getService(): ProfessorService {
    return this.injector.get(ProfessorService);
  }
  novaInstancia(): Professor {
    return new Professor();
  }


}

