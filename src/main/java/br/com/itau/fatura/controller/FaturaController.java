package br.com.itau.fatura.controller;

import br.com.itau.fatura.model.Fatura;
import br.com.itau.fatura.model.FaturaResponse;
import br.com.itau.fatura.service.FaturaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FaturaController {

    private final Logger logger = LoggerFactory.getLogger(FaturaController.class);
//1
    private final FaturaService faturaService;

    public FaturaController(FaturaService faturaService) {
        this.faturaService = faturaService;
    }

    @GetMapping("/v1/cartoes/{id}/faturas")
    public ResponseEntity<List<FaturaResponse>> exibeFatura(@PathVariable("id") @Valid @NotBlank String numeroCartao) { //1
        List<Fatura> compras = faturaService.buscaFatura(numeroCartao); //1

        if (compras == null) { //1
            return ResponseEntity.badRequest().build();
        }

        List<FaturaResponse> faturaResponse = compras.stream().map(FaturaResponse::new).collect(Collectors.toList()); //1

        logger.info("{} detalhamento(s) de compras para o cartão com final {}", faturaResponse.size(), numeroCartao.substring(24));
        return ResponseEntity.ok(faturaResponse);
    }
}
