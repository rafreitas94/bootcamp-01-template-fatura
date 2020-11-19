package br.com.itau.fatura.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RenegociacaoResponse {

    @NotNull
    @Positive
    private final int quantidadeParcelas;
    @NotNull
    @Positive
    private final BigDecimal valorParcela;
    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private final LocalDate dataDePagamento;

    public RenegociacaoResponse(@NotNull @Positive int quantidadeParcelas, @NotNull @Positive BigDecimal valorParcela, @NotNull LocalDate dataDePagamento) {
        this.quantidadeParcelas = quantidadeParcelas;
        this.valorParcela = valorParcela;
        this.dataDePagamento = dataDePagamento;
    }

    public RenegociacaoResponse(Renegociacao renegociacao) {
        this.quantidadeParcelas = renegociacao.getQuantidadeParcelas();
        this.valorParcela = renegociacao.getValorParcela();
        this.dataDePagamento = renegociacao.getDataDePagamento();
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }
}
