package br.com.itau.fatura.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ParcelaRequest {

    @NotNull
    @Positive
    private final int quantidadeParcelas;
    @NotNull
    @Positive
    private final BigDecimal valorParcela;

    public ParcelaRequest(@NotNull @Positive int quantidadeParcelas, @NotNull @Positive BigDecimal valorParcela) {
        this.quantidadeParcelas = quantidadeParcelas;
        this.valorParcela = valorParcela;
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public Parcela toModel() {
        return new Parcela(this.quantidadeParcelas, this.valorParcela);
    }
}
