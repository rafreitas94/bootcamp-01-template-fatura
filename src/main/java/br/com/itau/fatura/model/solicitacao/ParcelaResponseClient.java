package br.com.itau.fatura.model.solicitacao;

import javax.validation.constraints.NotBlank;

public class ParcelaResponseClient {

    @NotBlank
    private ResultadoParcelaClient resultado;

    @Deprecated
    public ParcelaResponseClient() {
    }

    public ParcelaResponseClient(@NotBlank ResultadoParcelaClient resultado) {
        this.resultado = resultado;
    }

    public ResultadoParcelaClient getResultado() {
        return resultado;
    }
}
