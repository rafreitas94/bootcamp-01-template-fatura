package br.com.itau.fatura.model.listener;

import javax.validation.constraints.NotBlank;

public class CartaoListener {

    @NotBlank
    private String id;
    @NotBlank
    private String email;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
