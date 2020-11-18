package br.com.itau.fatura.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class FaturaResponse {

    @NotBlank
    private final String id;
    @NotNull
    private final List<CompraResponse> compras;
    @NotNull
    private final List<ParcelaResponse> parcelas;

    public FaturaResponse(@NotBlank String id, @NotNull List<CompraResponse> compras, @NotNull List<ParcelaResponse> parcelas) {
        this.id = id;
        this.compras = compras;
        this.parcelas = parcelas;
    }

    public FaturaResponse(Fatura fatura) {
        this.id = fatura.getId();
        this.compras = fatura.getCompras().stream().map(CompraResponse::new).collect(Collectors.toList());
        this.parcelas = fatura.getParcela().stream().map(ParcelaResponse::new).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public List<CompraResponse> getCompras() {
        return compras;
    }

    public List<ParcelaResponse> getParcelas() {
        return parcelas;
    }
}
