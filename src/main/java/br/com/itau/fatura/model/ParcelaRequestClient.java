package br.com.itau.fatura.model;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class ParcelaRequestClient {

    @NotBlank
    private final String identificadorDaFatura;
    @NotBlank
    private final int quantidade;
    @NotBlank
    private final BigDecimal valor;

    public ParcelaRequestClient(@NotBlank String identificadorDaFatura, @NotBlank int quantidade, @NotBlank BigDecimal valor) {
        this.identificadorDaFatura = identificadorDaFatura;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getIdentificadorDaFatura() {
        return identificadorDaFatura;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
