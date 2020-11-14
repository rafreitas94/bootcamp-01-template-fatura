package br.com.itau.fatura.model.solicitacao;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CarteiraDigitalResponseClient {

    @NotBlank
    private final String id;
    @NotBlank
    @Email
    private final String email;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private final LocalDateTime associadaEm;
    @NotBlank
    private final String emissor;

    public CarteiraDigitalResponseClient(@NotBlank String id, @NotBlank @Email String email, LocalDateTime associadaEm, @NotBlank String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }
}
