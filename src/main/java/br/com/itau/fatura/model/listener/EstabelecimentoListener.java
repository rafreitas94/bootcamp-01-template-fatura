package br.com.itau.fatura.model.listener;

import javax.validation.constraints.NotBlank;

public class EstabelecimentoListener {

    @NotBlank
    private String nome;
    @NotBlank
    private String cidade;
    @NotBlank
    private String endereco;

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }
}
