package br.com.itau.fatura.component;

import br.com.itau.fatura.model.Compra;
import br.com.itau.fatura.model.Fatura;
import br.com.itau.fatura.model.listener.EventoTransacaoListener;
import br.com.itau.fatura.service.FaturaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
public class ListenerDeTransacao {

    private  final Logger logger = LoggerFactory.getLogger(ListenerDeTransacao.class);

    private final EntityManager entityManager;
//1
    private final FaturaService faturaService;

    public ListenerDeTransacao(EntityManager entityManager, FaturaService faturaService) {
        this.entityManager = entityManager;
        this.faturaService = faturaService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    @Transactional
    public void escutar(EventoTransacaoListener eventoTransacaoListener) { //1
        logger.info("Evento {} recebido com sucesso!", eventoTransacaoListener.getId());
        Compra compra = eventoTransacaoListener.toModel(); //1
        logger.info("Evento {} convertido com sucesso!", eventoTransacaoListener.getId());

        Fatura fatura = faturaService.buscaFatura(compra.getCartao().getIdCartao()); //1
        fatura.carregaCompra(compra);

        if (fatura.getId() == null) { //1
            entityManager.persist(fatura);
        } else { //1
            entityManager.merge(fatura);
        }

        logger.info("Evento {} da fatura id={} persistido com sucesso!", eventoTransacaoListener.getId(), fatura.getId());
    }
}
