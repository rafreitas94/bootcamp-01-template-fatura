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
import java.util.List;

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

        List<Fatura> compras = faturaService.buscaFatura(numeroCartao); //1

        if (compras == null) { //1
            return ResponseEntity.badRequest().build();
        }

        List<Fatura> listaDeComprasComFiltro = faturaService.filtraFaturaPorUltimasCompras(compras);
        logger.info("{} compras filtradas para o cartão com final {}", listaDeComprasComFiltro.size(), numeroCartao.substring(24));

        BigDecimal valorTotalUtilizado = faturaService.calculaValorTotalUtilizado(listaDeComprasComFiltro);

        Limite limite = new Limite(valorDoLimite, valorTotalUtilizado, compras); //1
        limite.calculaLimiteDisponivel();

        logger.info("Limite valorDoLimite={} valorTotalUtilizado={} disponivel={} para o cartão com final {}", limite.getLimite(), limite.getValorTotalUtilizado(), limite.getDisponivel(), numeroCartao.substring(24));
        return ResponseEntity.ok(new LimiteResponse(limite));
    }
}
