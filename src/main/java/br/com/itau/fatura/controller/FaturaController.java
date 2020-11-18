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

@RestController
public class FaturaController {

    private final Logger logger = LoggerFactory.getLogger(FaturaController.class);
    //1
    private final FaturaService faturaService;

    public FaturaController(FaturaService faturaService) {
        this.faturaService = faturaService;
    }

    @GetMapping("/v1/cartoes/{id}/faturas")
    public ResponseEntity<FaturaResponse> exibeFatura(@PathVariable("id") @Valid @NotBlank String numeroCartao) { //1
        Fatura fatura = faturaService.buscaFatura(numeroCartao); //1

        if (fatura.getId() == null) { //1
            logger.error("Fatura não encontrada.");
            return ResponseEntity.badRequest().build();
        }

        logger.info("Fatura id={} atrelada ao cartão com final {} encontrada com sucesso!", fatura.getId(), numeroCartao.substring(24));
        return ResponseEntity.ok(new FaturaResponse(fatura));
    }
}
