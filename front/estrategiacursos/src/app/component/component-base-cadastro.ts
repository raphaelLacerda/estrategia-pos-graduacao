import { ActivatedRoute, ParamMap } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Directive, Injector, OnInit, ViewChild } from '@angular/core';
import { Location } from '@angular/common';
import { FormGroup, NgForm } from '@angular/forms';
import { ModelBase } from '../model/model-base.model';
import { ServiceBase } from '../service/service-base.service';

@Directive()
export abstract class ComponentBaseCadastroDirective<T extends ModelBase>
  implements OnInit {
  objetos: T[] = [];
  objeto: T;

  constructor(private messageService: MessageService, protected injector:Injector) {}

  abstract getService(): ServiceBase<T>;
  abstract novaInstancia(): T;

  ngOnInit(): void {
    this.objeto = this.novaInstancia();
    this.listar();
  }

  listar(): void {
    this.getService().listar().then(l => this.objetos = l)
      .catch(e => console.log(e));
  }

  editar(objeto: T): void {
    this.objeto = objeto;
  }

  async onSubmit(form: NgForm): Promise<void> {
    try {
      this.objeto = await this.getService().salvar(this.objeto);
      this.messageService.add({
        severity: 'success',
        summary: 'Sucesso na atualização',
        detail: 'Registro atualizado com sucesso ' + this.objeto.id,
      });

      form.resetForm();
      this.ngOnInit();
    } catch (e) {
      console.log(e);
      this.messageService.add({
        severity: 'error',
        summary: 'Problema ao salvar registro!',
        detail: 'Erro ' + e.error.error + '-'+e.error.status ,
      });
    }
  }

  async remover(objetoEscolhido: T): Promise<void> {
    try {
      await this.getService().remover(objetoEscolhido.id);
      this.messageService.add({
        severity: 'success',
        summary: 'Sucesso na removação',
        detail: 'Registro removido com sucesso ' + objetoEscolhido.id,
      });
      this.ngOnInit();
    } catch (e) {
      console.log(e);
      this.messageService.add({
        severity: 'error',
        summary: 'Problema ao remover registro!',
        detail: 'Erro ' + e.error.error + '-'+e.error.status ,
      });
    }

  }
}
