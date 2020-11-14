package br.com.itau.fatura.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FaturaResponse {

    @NotNull
    @Positive
    private final BigDecimal valor;
    @NotNull
    private final EstabelecimentoResponse estabelecimento;
    @NotNull
    private final CartaoResponse cartao;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private final LocalDateTime efetivadaEm;

    public FaturaResponse(@NotNull @Positive BigDecimal valor, @NotNull EstabelecimentoResponse estabelecimento, @NotNull CartaoResponse cartao, LocalDateTime efetivadaEm) {
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoResponse getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoResponse getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public FaturaResponse(Fatura compra) {
        this.valor = compra.getValor();
        this.estabelecimento = new EstabelecimentoResponse(compra.getEstabelecimento());
        this.cartao = new CartaoResponse(compra.getCartao());
        this.efetivadaEm = compra.getEfetivadaEm();
    }
}
