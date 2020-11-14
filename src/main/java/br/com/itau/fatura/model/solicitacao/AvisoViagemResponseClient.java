package br.com.itau.fatura.model.solicitacao;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemResponseClient {

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private final LocalDate validoAte;
    @NotBlank
    private final String destino;

    public AvisoViagemResponseClient(@NotNull LocalDate validoAte, @NotBlank String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
