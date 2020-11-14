package br.com.itau.fatura.model.solicitacao;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class CartaoResponseClient {

    @NotBlank
    private final String id;
    @NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private final String emitidoEm;
    @NotBlank
    private final String titular;
    @NotNull
    private final List<BloqueioResponseClient> bloqueios;
    @NotNull
    private final List<AvisoViagemResponseClient> avisos;
    @NotNull
    private final List<CarteiraDigitalResponseClient> carteiras;
    @NotNull
    private final List<ParcelaResponseClient> parcelas;
    @NotNull
    private final BigDecimal limite;
    @NotNull
    private final RenegociacaoResponseClient renegociacao;
    @NotNull
    private final VencimentoResponseClient vencimento;
    @NotBlank
    private final String idProposta;

    public CartaoResponseClient(@NotBlank String id, @NotBlank String emitidoEm, @NotBlank String titular, @NotNull List<BloqueioResponseClient> bloqueios,
                                @NotNull List<AvisoViagemResponseClient> avisos, @NotNull List<CarteiraDigitalResponseClient> carteiras, @NotNull List<ParcelaResponseClient> parcelas,
                                @NotNull BigDecimal limite, @NotNull RenegociacaoResponseClient renegociacao, @NotNull VencimentoResponseClient vencimento, @NotBlank String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public String getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<BloqueioResponseClient> getBloqueios() {
        return bloqueios;
    }

    public List<AvisoViagemResponseClient> getAvisos() {
        return avisos;
    }

    public List<CarteiraDigitalResponseClient> getCarteiras() {
        return carteiras;
    }

    public List<ParcelaResponseClient> getParcelas() {
        return parcelas;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public RenegociacaoResponseClient getRenegociacao() {
        return renegociacao;
    }

    public VencimentoResponseClient getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
