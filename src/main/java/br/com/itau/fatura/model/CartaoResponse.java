package br.com.itau.fatura.model;

import javax.validation.constraints.NotBlank;

public class CartaoResponse {

    @NotBlank
    private final String email;

    public CartaoResponse(@NotBlank String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public CartaoResponse(Cartao cartao) {
        this.email = cartao.getEmail();
    }
}
