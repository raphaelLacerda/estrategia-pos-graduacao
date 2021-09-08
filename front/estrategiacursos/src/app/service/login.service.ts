import { environment } from './../../environments/environment';
import { Usuario } from './../model/usuario.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private http: HttpClient) {}

  async logar(usuario: Usuario) {
    try {
      const r: any = await this.http
        .post<Usuario>(environment.SERVICO_LOGIN, usuario)
        .toPromise();
      console.log(r);
      localStorage.setItem('token', r.token);
      return true;
    } catch (e) {
      console.log(e);
      return false;
    }
  }

  getToken() {
    return localStorage.getItem('token');
  }

  isLogado() {
    // return this.getToken() !== null;
    return true;
  }

  logout() {
    localStorage.removeItem('token');
  }
}
