package br.com.itau.fatura.controller;

import br.com.itau.fatura.model.Fatura;
import br.com.itau.fatura.model.Renegociacao;
import br.com.itau.fatura.model.RenegociacaoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URI;

@RestController
public class RenegociacaoController {

    private final Logger logger = LoggerFactory.getLogger(RenegociacaoController.class);

    private final EntityManager entityManager;

    public RenegociacaoController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
//1
    @PostMapping("/v1/cartoes/{idCartao}/faturas/{idFatura}/renegociacao")
    @Transactional
    public ResponseEntity<?> criaRenegociacao(@PathVariable("idCartao") @Valid @NotBlank String numeroCartao, @PathVariable("idFatura") @Valid @NotBlank String numeroFatura, @Valid @RequestBody RenegociacaoRequest renegociacaoRequest, UriComponentsBuilder builder) {
        Renegociacao renegociacao = renegociacaoRequest.toModel(); //1

        renegociacao.aplicaDataPagamento();

        Fatura fatura = entityManager.find(Fatura.class, numeroFatura); //1

        if (fatura == null) { //1
            logger.error("Fatura não encontrada.");
            return ResponseEntity.notFound().build();
        }

        if (!fatura.numeroDoCartao().equals(numeroCartao)) { //1
            logger.error("Cartão não encontrado.");
            return ResponseEntity.notFound().build();
        }

        fatura.carregaRenegociacao(renegociacao);

        entityManager.persist(renegociacao);
        logger.info("Renegociação id={} dataDePagamento={} criada com sucesso!", renegociacao.getId(), renegociacao.getDataDePagamento());

        entityManager.merge(fatura);
        logger.info("Renegociação id={} dataDePagamento={} atrelada a fatura id={} com sucesso!", renegociacao.getId(), renegociacao.getDataDePagamento(), fatura.getId());

        URI uri = builder.path("/v1/renegociacoes/{id}").buildAndExpand(renegociacao.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
