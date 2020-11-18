package br.com.itau.fatura.model;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class FaturaResponse {

    @NotNull
    private final List<CompraResponse> compras;

    public FaturaResponse(@NotNull List<CompraResponse> compras) {
        this.compras = compras;
    }

    public List<CompraResponse> getCompras() {
        return compras;
    }

    public FaturaResponse(Fatura fatura) {
        this.compras = fatura.getCompras().stream().map(CompraResponse::new).collect(Collectors.toList());
    }
}
