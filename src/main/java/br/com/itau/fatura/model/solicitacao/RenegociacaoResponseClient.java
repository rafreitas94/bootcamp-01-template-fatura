package br.com.itau.fatura.model.solicitacao;

import javax.validation.constraints.NotBlank;

public class RenegociacaoResponseClient {

    @NotBlank
    private ResultadoParcelaClient resultado;

    @Deprecated
    public RenegociacaoResponseClient() {
    }

    public RenegociacaoResponseClient(@NotBlank ResultadoParcelaClient resultado) {
        this.resultado = resultado;
    }

    public ResultadoParcelaClient getResultado() {
        return resultado;
    }
}
