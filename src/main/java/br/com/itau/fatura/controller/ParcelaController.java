package br.com.itau.fatura.controller;

import br.com.itau.fatura.client.ParcelaClient;
import br.com.itau.fatura.model.Fatura;
import br.com.itau.fatura.model.Parcela;
import br.com.itau.fatura.model.ParcelaRequest;
import br.com.itau.fatura.model.solicitacao.ParcelaResponseClient;
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
public class ParcelaController {

    private final Logger logger = LoggerFactory.getLogger(ParcelaController.class);

    private final EntityManager entityManager;
//1
    private final ParcelaClient parcelaClient;

    public ParcelaController(EntityManager entityManager, ParcelaClient parcelaClient) {
        this.entityManager = entityManager;
        this.parcelaClient = parcelaClient;
    }

    @PostMapping("/v1/cartoes/{idCartao}/faturas/{idFatura}/parcelas")
    @Transactional
    public ResponseEntity<?> criaParcela(@PathVariable("idCartao") @Valid @NotBlank String numeroCartao,
                                                       @PathVariable("idFatura") @Valid @NotBlank String numeroFatura,
                                                       @Valid @RequestBody ParcelaRequest parcelaRequest,
                                                       UriComponentsBuilder builder) { //1
        Parcela parcela = parcelaRequest.toModel(); //1

        Fatura fatura = entityManager.find(Fatura.class, numeroFatura); //1

        if (fatura == null) { //1
            logger.error("Fatura não encontrada.");
            return ResponseEntity.notFound().build();
        }

        if (!fatura.numeroDoCartao().equals(numeroCartao)) { //1
            logger.error("Cartão não encontrado.");
            return ResponseEntity.notFound().build();
        }
//1
        ParcelaResponseClient parcelaResponseClient = parcelaClient.notificaParcela(fatura.getId(), parcela, numeroCartao);
        logger.info("Parcela status={} valor={} atrelada a fatura id={} criada com sucesso!", parcelaResponseClient.getResultado(), parcela.getValorParcela(), fatura.getId());

        entityManager.persist(parcela);

        fatura.carregaParcela(parcela);

        entityManager.merge(fatura);
        logger.info("Parcela id={} valor={} atrelada a fatura id={} criada com sucesso!", parcela.getId(), parcela.getValorParcela(), fatura.getId());

        URI uri = builder.path("/v1/parcelas/{idParcela}").buildAndExpand(parcela.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
