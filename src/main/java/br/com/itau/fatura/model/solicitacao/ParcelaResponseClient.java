package br.com.itau.fatura.model.solicitacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ParcelaResponseClient {

    @NotBlank
    private final String idParcela;
    @NotNull
    private final Integer quantidade;
    @NotNull
    private final BigDecimal valor;

    public ParcelaResponseClient(@NotBlank String idParcela, @NotNull Integer quantidade, @NotNull BigDecimal valor) {
        this.idParcela = idParcela;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getIdParcela() {
        return idParcela;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
