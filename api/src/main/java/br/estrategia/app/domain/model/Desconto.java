package br.estrategia.app.domain.model;

import br.estrategia.app.domain.model.entidade.Concurso;

import java.math.BigDecimal;

public interface Desconto {

    BigDecimal aplicar(Concurso concurso);

    default BigDecimal calcular(BigDecimal valorBase, BigDecimal desconto){
        return valorBase
                .multiply(desconto)
                .divide(new BigDecimal(100));
    }
}
