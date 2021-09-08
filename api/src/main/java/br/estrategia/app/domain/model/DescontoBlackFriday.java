package br.estrategia.app.domain.model;

import br.estrategia.app.domain.model.entidade.Concurso;

import java.math.BigDecimal;


//Este IF não tem como retirar é um IF de CONDIÇÃO e não de OPÇÃO
public class DescontoBlackFriday implements Desconto {
    @Override
    public BigDecimal aplicar(Concurso concurso) {
        if(concurso.getDiaDoLancamento().getMonthValue()==11){
            return this.calcular(concurso.getValorBruto(), new BigDecimal(70));
        }
        return BigDecimal.ZERO;

    }
}
