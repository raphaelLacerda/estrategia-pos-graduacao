package br.estrategia.app.domain.model;

import br.estrategia.app.domain.model.entidade.Concurso;

import java.math.BigDecimal;

public class DescontoPandemia implements Desconto {
    @Override
    public BigDecimal aplicar(Concurso concurso) {
        if(concurso.getDiaDoLancamento().getYear()==2020){
            return this.calcular(concurso.getValorBruto(), new BigDecimal(50));
        }
        return BigDecimal.ZERO;

    }
}
