import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Usuario } from './../../model/usuario.model';
import { LoginService } from './../../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  usuario: Usuario = new Usuario();
  constructor(
    private loginService: LoginService,
    private router: Router,
    protected route: ActivatedRoute,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      let logout = params['logout'];
      if (logout) {
        this.loginService.logout();
      }
    });
  }

  async onSubmit() {
    try {
      console.log('inicando login');
      const r = await this.loginService.logar(this.usuario);
      console.log('login realizado com sucesso');
      if (r) {
        this.router.navigate(['home']);
      } else {
        this.messageService.add({
          severity: 'error',
          summary: 'Erro no login',
          detail: 'Login Inválido',
        });
      }
    } catch (e) {
      console.log('Erro ao fazer o login');
      console.log(e);
    }
  }
}
