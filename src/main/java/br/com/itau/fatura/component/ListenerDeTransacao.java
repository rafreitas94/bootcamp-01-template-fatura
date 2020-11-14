package br.com.itau.fatura.component;

import br.com.itau.fatura.model.Fatura;
import br.com.itau.fatura.model.listener.EventoTransacaoListener;
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

    public ListenerDeTransacao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    @Transactional
    public void escutar(EventoTransacaoListener eventoTransacaoListener) {
        logger.info("Evento {} recebido com sucesso!", eventoTransacaoListener.getId());
        Fatura compra = eventoTransacaoListener.toModel();
        logger.info("Evento {} convertido com sucesso!", eventoTransacaoListener.getId());

        entityManager.persist(compra);
        logger.info("Evento {} persistido com sucesso!", eventoTransacaoListener.getId());
    }
}
