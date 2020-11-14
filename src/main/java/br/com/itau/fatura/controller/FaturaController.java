package br.com.itau.fatura.controller;

import br.com.itau.fatura.client.CartaoClient;
import br.com.itau.fatura.model.Fatura;
import br.com.itau.fatura.model.FaturaResponse;
import br.com.itau.fatura.model.Limite;
import br.com.itau.fatura.model.solicitacao.CartaoResponseClient;
import br.com.itau.fatura.service.FaturaService;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FaturaController {

    private final Logger logger = LoggerFactory.getLogger(FaturaController.class);

    private final FaturaService faturaService;

    private final CartaoClient cartaoClient;

    public FaturaController(FaturaService faturaService, CartaoClient cartaoClient) {
        this.faturaService = faturaService;
        this.cartaoClient = cartaoClient;
    }

    @GetMapping("/v1/cartoes/{id}/faturas")
    public ResponseEntity<List<FaturaResponse>> exibeFatura(@PathVariable("id") @Valid @NotBlank String numeroCartao) { //1
        List<Fatura> compras = faturaService.buscaCompras(numeroCartao);

        if (compras == null) {
            ResponseEntity.badRequest().build();
        }

        List<FaturaResponse> faturaResponse = compras.stream().map(FaturaResponse::new).collect(Collectors.toList()); //1

        logger.info("{} detalhamento(s) de compras para o cartão com final {}", faturaResponse.size(), numeroCartao.substring(24));
        return ResponseEntity.ok(faturaResponse);
    }

    @GetMapping("/v1/cartoes/{id}/limites")
    public ResponseEntity<?> exibeLimite(@PathVariable("id") String numeroCartao) { //1 ToDo
        CartaoResponseClient cartaoResponseClient; //1
        try { //1
            cartaoResponseClient = cartaoClient.buscaCartao(numeroCartao);
        } catch (FeignException feignException) { //1 1
            //ToDo
            logger.error("Erro: {}", feignException.getMessage());
            return ResponseEntity.badRequest().body("Implementar");
        }

        BigDecimal valorLimite = cartaoResponseClient.getLimite();

        List<Fatura> compras = faturaService.buscaCompras(numeroCartao); //1

        if (compras == null) { //1
            ResponseEntity.badRequest().build();
        }

        List<Fatura> listaDeComprasComFiltro = new ArrayList<>();
        if (compras.size() > 10) { //1
            for (int i = compras.size(); i > (compras.size() - 10); i--) { //1
                listaDeComprasComFiltro.add(compras.get(i-1));
            }
        } else { //1
            listaDeComprasComFiltro = compras;
        }

        BigDecimal saldoTotal = new BigDecimal(0);
        for (Fatura detalhamentoCompra : listaDeComprasComFiltro) { //1
            saldoTotal = saldoTotal.add(detalhamentoCompra.getValor());
        }

        Limite limite = new Limite(valorLimite, saldoTotal, compras); //1
        limite.calculaLimiteDisponivel();

        return ResponseEntity.ok(limite);
    }
}
