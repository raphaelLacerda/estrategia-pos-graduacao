import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent implements OnInit {
  items: MenuItem[] = [];

  constructor() {}

  ngOnInit(): void {
    this.items = [
      {
        label: 'Home',
        icon: 'pi pi-fw pi-home',
        routerLink: '/'
      },
      {
        label: 'Cadastro',
        icon: 'pi pi-fw pi-pencil',
        items: [
          { label: 'Alunos', icon: 'pi pi-fw pi-users', routerLink: '/aluno' },
          { label: 'Concurso', icon: 'pi pi-fw pi-sitemap', routerLink: '/concurso' },
          { label: 'Professor', icon: 'pi pi-fw pi-desktop', routerLink: '/professor' },
          { label: 'Disciplina', icon: 'pi pi-fw pi-palette', routerLink: '/disciplina' },
        ],
      },
      {
        label: 'Concurso',
        icon: 'pi pi-fw pi-sitemap',
        items: [
          { label: 'Matricula', icon: 'pi pi-fw pi-book', routerLink: '/matricula' },
          { label: 'Grade', icon: 'pi pi-fw pi-briefcase', routerLink: '/grade' },
        ],
      },
      {
        label: 'Sistema',
        icon: 'pi pi-fw pi-microsoft',
        items: [
          { label: 'Login', icon: 'pi pi-fw pi-sign-in', routerLink: '/login' },
          { label: 'Logout', icon: 'pi pi-fw pi-sign-out', routerLink: '/login', queryParams: {logout: 'true'} },
        ],
      },
    ];
  }
}
