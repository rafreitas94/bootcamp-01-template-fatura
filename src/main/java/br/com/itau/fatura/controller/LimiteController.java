package br.com.itau.fatura.controller;

import br.com.itau.fatura.model.Fatura;
import br.com.itau.fatura.model.Limite;
import br.com.itau.fatura.model.LimiteResponse;
import br.com.itau.fatura.service.FaturaService;
import br.com.itau.fatura.service.LimiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@RestController
public class LimiteController {

    private final Logger logger = LoggerFactory.getLogger(LimiteController.class);
    //1
    private final LimiteService limiteService;
    //1
    private final FaturaService faturaService;

    public LimiteController(LimiteService limiteService, FaturaService faturaService) {
        this.limiteService = limiteService;
        this.faturaService = faturaService;
    }

    @GetMapping("/v1/cartoes/{id}/limites")
    public ResponseEntity<LimiteResponse> exibeLimite(@PathVariable("id") @Valid @NotBlank String numeroCartao) { //1
        BigDecimal valorDoLimite = limiteService.buscarValorDoLimite(numeroCartao); //1

        Fatura fatura = faturaService.buscaFatura(numeroCartao); //1

        if (fatura == null) { //1
            logger.error("Fatura não encontrada.");
            return ResponseEntity.badRequest().build();
        }

        BigDecimal valorTotalUtilizado = limiteService.calculaValorTotalUtilizado(fatura.getCompras());

        Fatura faturaComFiltro = faturaService.filtraFaturaPorUltimasCompras(fatura);
        logger.info("{} compras filtradas para o cartão com final {}", faturaComFiltro.getCompras().size(), numeroCartao.substring(24));

        Limite limite = new Limite(valorDoLimite, valorTotalUtilizado, faturaComFiltro.getCompras()); //1
        limite.calculaLimiteDisponivel();

        logger.info("Limite valorDoLimite={} valorTotalUtilizado={} disponivel={} para o cartão com final {}", limite.getLimite(), limite.getValorTotalUtilizado(), limite.getDisponivel(), numeroCartao.substring(24));
        return ResponseEntity.ok(new LimiteResponse(limite));
    }
}
