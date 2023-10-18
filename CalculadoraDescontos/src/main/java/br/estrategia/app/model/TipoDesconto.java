package br.estrategia.app.model;

import java.math.BigDecimal;

public enum TipoDesconto {
    PANDEMIA(BigDecimal.valueOf(50)), BLACK_FRIDAY(BigDecimal.valueOf(70)), NORMAL(BigDecimal.TEN);

    private BigDecimal valor;
    TipoDesconto(BigDecimal valor){
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
