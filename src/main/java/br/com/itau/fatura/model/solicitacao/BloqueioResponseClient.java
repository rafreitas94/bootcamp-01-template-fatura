package br.com.itau.fatura.model.solicitacao;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BloqueioResponseClient {

    @NotBlank
    private final String id;
    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private final LocalDateTime bloqueadoEm;
    @NotBlank
    private final String sistemaResponsavel;
    @NotNull
    private final Boolean ativo;

    public BloqueioResponseClient(@NotBlank String id, @NotNull LocalDateTime bloqueadoEm, @NotBlank String sistemaResponsavel, @NotNull Boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public String getId() {
        return id;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}
