package br.com.itau.fatura.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ParcelaResponse {

    @NotNull
    @Positive
    private final int quantidadeParcelas;
    @NotNull
    @Positive
    private final BigDecimal valorParcela;

    public ParcelaResponse(@NotNull @Positive int quantidadeParcelas, @NotNull @Positive BigDecimal valorParcela) {
        this.quantidadeParcelas = quantidadeParcelas;
        this.valorParcela = valorParcela;
    }

    public ParcelaResponse(Parcela parcela) {
        this.quantidadeParcelas = parcela.getQuantidadeParcelas();
        this.valorParcela = parcela.getValorParcela();
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }
}
