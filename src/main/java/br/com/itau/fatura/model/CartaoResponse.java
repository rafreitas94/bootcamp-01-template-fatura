package br.com.itau.fatura.model;

import javax.validation.constraints.NotBlank;

public class CartaoResponse {

    @NotBlank
    private final String idCartao;
    @NotBlank
    private final String email;

    public CartaoResponse(@NotBlank String idCartao, @NotBlank String email) {
        this.idCartao = idCartao;
        this.email = email;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public String getEmail() {
        return email;
    }

    public CartaoResponse(Cartao cartao) {
        this.idCartao = cartao.getIdCartao();
        this.email = cartao.getEmail();
    }
}
