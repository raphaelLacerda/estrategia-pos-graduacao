import { HttpClient } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { ModelBase } from '../model/model-base.model';

@Injectable()
export abstract class ServiceBase<T extends ModelBase> {
  constructor(protected http: HttpClient, protected injector: Injector) {}

  abstract getURL(): string;

  salvar(objeto: T): Promise<T> {
    if (objeto.id) {
      return this.http
        .put<T>(this.getURL() + '/' + objeto.id, objeto)
        .toPromise();
    }
    return this.http.post<T>(this.getURL(), objeto).toPromise();
  }

  buscar(id: number): Promise<T> {
    return this.http.get<T>(this.getURL() + '/' + id).toPromise();
  }

  listar(): Promise<T[]> {
    return this.http.get<T[]>(this.getURL()).toPromise();
  }

  remover(id: number): Promise<any> {
    return this.http.delete<any>(this.getURL() + '/' + id).toPromise();
  }
}
