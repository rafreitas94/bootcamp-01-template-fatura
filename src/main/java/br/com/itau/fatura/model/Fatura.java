package br.com.itau.fatura.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Fatura {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private List<Compra> compras;
    @NotNull
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Parcela> parcela;
    @OneToOne
    private Renegociacao renegociacao;

    @Deprecated
    public Fatura() {
    }

    public Fatura(@NotNull List<Compra> compras, @NotNull List<Parcela> parcela) {
        this.compras = compras;
        this.parcela = parcela;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public List<Parcela> getParcela() {
        return parcela;
    }

    public void setParcela(List<Parcela> parcela) {
        this.parcela = parcela;
    }

    public Renegociacao getRenegociacao() {
        return renegociacao;
    }

    public void setRenegociacao(Renegociacao renegociacao) {
        this.renegociacao = renegociacao;
    }

    public String numeroDoCartao() {
        Assert.notEmpty(this.compras, "Não é possível obter o número do cartão pois a lista de compras está vazia");
        return this.getCompras().get(0).getCartao().getIdCartao();
    }

    public int quantidadeDeCompras() {
        return this.compras.size();
    }

    public void carregaCompra(Compra compra) {
        this.compras.add(compra);
    }

    public void carregaParcela(Parcela parcela) {
        this.parcela.add(parcela);
    }

    public void carregaRenegociacao(Renegociacao renegociacao) {
        this.renegociacao = renegociacao;
    }

    public boolean verificaRenegociacaoVazia(String numeroCartao) {
        if (this.renegociacao != null) { //1
            return this.numeroDoCartao().equals(numeroCartao);
        }
        return false;
    }

    public boolean verificaComprasVazia(String numeroCartao) {
        if (!this.compras.isEmpty()) { //1
            return this.numeroDoCartao().equals(numeroCartao);
        }
        return false;
    }
}
