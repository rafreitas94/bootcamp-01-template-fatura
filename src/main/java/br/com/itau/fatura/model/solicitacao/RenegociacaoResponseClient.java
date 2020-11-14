package br.com.itau.fatura.model.solicitacao;

import java.math.BigDecimal;

public class RenegociacaoResponseClient {

    private final String id;
    private final Integer quantidade;
    private final BigDecimal valor;
    private final String dataDeCriacaoRenegociacao;

    public RenegociacaoResponseClient(String id, Integer quantidade, BigDecimal valor, String dataDeCriacaoRenegociacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacaoRenegociacao = dataDeCriacaoRenegociacao;
    }

    public String getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDataDeCriacaoRenegociacao() {
        return dataDeCriacaoRenegociacao;
    }
}
