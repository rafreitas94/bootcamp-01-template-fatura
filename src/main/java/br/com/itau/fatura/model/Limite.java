package br.com.itau.fatura.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

public class Limite {

    @NotNull
    @Positive
    private BigDecimal limite;
    @NotNull
    @Positive
    private BigDecimal valorTotalUtilizado;
    @NotNull
    private BigDecimal disponivel;
    @NotNull
    private List<Fatura> compras;

    public Limite(BigDecimal limite, BigDecimal valorTotalUtilizado, List<Fatura> compras) {
        this.limite = limite;
        this.valorTotalUtilizado = valorTotalUtilizado;
        this.compras = compras;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public BigDecimal getValorTotalUtilizado() {
        return valorTotalUtilizado;
    }

    public void setValorTotalUtilizado(BigDecimal valorTotalUtilizado) {
        this.valorTotalUtilizado = valorTotalUtilizado;
    }

    public BigDecimal getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(BigDecimal disponivel) {
        this.disponivel = disponivel;
    }

    public List<Fatura> getCompras() {
        return compras;
    }

    public void setCompras(List<Fatura> compras) {
        this.compras = compras;
    }

    public void calculaLimiteDisponivel() {
        this.disponivel = this.limite.subtract(this.valorTotalUtilizado);
    }
}
