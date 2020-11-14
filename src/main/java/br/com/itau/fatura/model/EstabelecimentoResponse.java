package br.com.itau.fatura.model;

import javax.validation.constraints.NotBlank;

public class EstabelecimentoResponse {

    @NotBlank
    private final String nome;
    @NotBlank
    private final String cidade;
    @NotBlank
    private final String endereco;

    public EstabelecimentoResponse(@NotBlank String nome, @NotBlank String cidade, @NotBlank String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public EstabelecimentoResponse(Estabelecimento estabelecimento) {
        this.nome = estabelecimento.getNome();
        this.cidade = estabelecimento.getCidade();
        this.endereco = estabelecimento.getEndereco();
    }
}
