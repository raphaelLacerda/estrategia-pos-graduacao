package br.estrategia.app.domain.model;

import br.estrategia.app.domain.model.entidade.Concurso;

import java.math.BigDecimal;


//Este IF não tem como retirar é um IF de CONDIÇÃO e não de OPÇÃO
public class DescontoQuantidadeDisciplina implements Desconto {
    @Override
    public BigDecimal aplicar(Concurso concurso) {
        BigDecimal desconto;
        if (concurso.getDisciplinas().size() >= 20) {
            desconto = new BigDecimal("20");
        } else if (concurso.getDisciplinas().size() >= 10) {
            desconto = new BigDecimal("10");
        } else {
            desconto = new BigDecimal("5");
        }
        return this.calcular(concurso.getValorBruto(), desconto);

    }
}
