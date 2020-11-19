package br.com.itau.fatura.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class RenegociacaoRequest {

    @NotNull
    @Positive
    private final int quantidadeParcelas;
    @NotNull
    @Positive
    private final BigDecimal valorParcela;

    public RenegociacaoRequest(@NotNull @Positive int quantidadeParcelas, @NotNull @Positive BigDecimal valorParcela) {
        this.quantidadeParcelas = quantidadeParcelas;
        this.valorParcela = valorParcela;
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public Renegociacao toModel() {
        return new Renegociacao(this.quantidadeParcelas, this.valorParcela);
    }
}
