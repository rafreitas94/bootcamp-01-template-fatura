package br.com.itau.fatura.model;

import javax.validation.constraints.NotBlank;

public class EstabelecimentoResponse {

    @NotBlank
    private final String nome;
    @NotBlank
    private final String cidade;

    public EstabelecimentoResponse(@NotBlank String nome, @NotBlank String cidade) {
        this.nome = nome;
        this.cidade = cidade;
    }

    public EstabelecimentoResponse(Estabelecimento estabelecimento) {
        this.nome = estabelecimento.getNome();
        this.cidade = estabelecimento.getCidade();
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }
}
