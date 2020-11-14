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
    private BigDecimal saldoAtual;
    @NotNull
    private BigDecimal disponivel;
    @NotNull
    private List<Fatura> compras;

    public Limite(BigDecimal limite, BigDecimal saldoAtual, List<Fatura> compras) {
        this.limite = limite;
        this.saldoAtual = saldoAtual;
        this.compras = compras;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public BigDecimal getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(BigDecimal saldoAtual) {
        this.saldoAtual = saldoAtual;
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
        this.disponivel = this.limite.subtract(this.saldoAtual);
    }
}
