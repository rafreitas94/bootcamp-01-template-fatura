package br.com.itau.fatura.model.solicitacao;

public class VencimentoResponseClient {

    private final String id;
    private final Integer dia;
    private final String dataDeCriacao;

    public VencimentoResponseClient(String id, Integer dia, String dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public String getDataDeCriacao() {
        return dataDeCriacao;
    }
}
