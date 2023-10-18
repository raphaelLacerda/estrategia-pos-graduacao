package br.estrategia.app.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Desconto {

    private TipoDesconto tipoDesconto;
    private BigDecimal valorBruto;

    public Desconto(TipoDesconto tipoDesconto, BigDecimal valorBruto) {
        this.tipoDesconto = tipoDesconto;
        this.valorBruto = valorBruto;
    }

    public TipoDesconto getTipoDesconto() {
        return tipoDesconto;
    }

    public BigDecimal getValorBruto() {
        return valorBruto;
    }

    public BigDecimal getValorLiquido() {

        BigDecimal valorLiquido = valorBruto
                .subtract(tipoDesconto.getValor())
                .setScale(2, RoundingMode.HALF_UP);
        return valorLiquido;
    }
}
