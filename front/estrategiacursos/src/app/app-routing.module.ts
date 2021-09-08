import { MatriculaComponent } from './component/matricula/matricula.component';
import { GradeComponent } from './component/grade/grade.component';
import { ProfessorComponent } from './component/professor/professor.component';
import { DisciplinaComponent } from './component/disciplina/disciplina.component';
import { ConcursoComponent } from './component/concurso/concurso.component';
import { AlunoComponent } from './component/aluno/aluno.component';
// import { AuthGuard } from './auth.guard';
import { HomeComponent } from './component/home/home.component';
import { LoginComponent } from './component/login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    // canActivate: [AuthGuard],
    data: {  }
  },
  {
    path: 'home',
    component: HomeComponent,
    // canActivate: [AuthGuard],
    data: {  }
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [],
    data: {  }
  },
  {
    path: 'aluno',
    component: AlunoComponent,
    // canActivate: [AuthGuard],
    data: {  }
  },
  {
    path: 'concurso',
    component: ConcursoComponent,
    // canActivate: [AuthGuard],
    data: {  }
  },
  {
    path: 'disciplina',
    component: DisciplinaComponent,
    // canActivate: [AuthGuard],
    data: {  }
  },
  {
    path: 'professor',
    component: ProfessorComponent,
    // canActivate: [AuthGuard],
    data: {  }
  },
  {
    path: 'matricula',
    component: MatriculaComponent,
    // canActivate: [AuthGuard],
    data: {  }
  },
  {
    path: 'grade',
    component: GradeComponent,
    // canActivate: [AuthGuard],
    data: {  }
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
