package br.com.itau.fatura.model.listener;

import br.com.itau.fatura.model.Cartao;
import br.com.itau.fatura.model.Compra;
import br.com.itau.fatura.model.Estabelecimento;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class EventoTransacaoListener {

    @NotBlank
    private String id;
    @NotNull
    private BigDecimal valor;
    @NotNull
    @JsonProperty(value = "estabelecimento")
    private EstabelecimentoListener estabelecimentoListener;
    @NotNull
    @JsonProperty(value = "cartao")
    private CartaoListener cartaoListener;
    private LocalDateTime efetivadaEm;

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoListener getEstabelecimentoListener() {
        return estabelecimentoListener;
    }

    public CartaoListener getCartaoListener() {
        return cartaoListener;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public Compra toModel() {
        return new Compra(this.id, this.valor.setScale(2, RoundingMode.HALF_DOWN),
                new Estabelecimento(this.estabelecimentoListener.getNome(), this.estabelecimentoListener.getCidade(), this.estabelecimentoListener.getEndereco()),
                new Cartao(this.cartaoListener.getId(), this.cartaoListener.getEmail()), this.efetivadaEm);
    }
}
