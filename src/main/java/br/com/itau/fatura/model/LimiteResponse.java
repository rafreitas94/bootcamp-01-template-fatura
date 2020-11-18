package br.com.itau.fatura.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class LimiteResponse {

    private final BigDecimal limite;
    private final BigDecimal valorTotalUtilizado;
    private final BigDecimal disponivel;
    @JsonProperty("compras")
    private final List<CompraResponse> comprasResponse;

    public LimiteResponse(BigDecimal limite, BigDecimal valorTotalUtilizado, BigDecimal disponivel, List<CompraResponse> comprasResponse) {
        this.limite = limite;
        this.valorTotalUtilizado = valorTotalUtilizado;
        this.disponivel = disponivel;
        this.comprasResponse = comprasResponse;
    }

    public LimiteResponse(Limite limite) {
        this.limite = limite.getLimite();
        this.valorTotalUtilizado = limite.getValorTotalUtilizado();
        this.disponivel = limite.getDisponivel();
        this.comprasResponse = limite.getCompras().stream().map(CompraResponse::new).collect(Collectors.toList());
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public BigDecimal getValorTotalUtilizado() {
        return valorTotalUtilizado;
    }

    public BigDecimal getDisponivel() {
        return disponivel;
    }

    public List<CompraResponse> getComprasResponse() {
        return comprasResponse;
    }
}
