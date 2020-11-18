package br.com.itau.fatura.component;

import br.com.itau.fatura.model.Compra;
import br.com.itau.fatura.model.Fatura;
import br.com.itau.fatura.model.listener.EventoTransacaoListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Component
public class ListenerDeTransacao {

    private  final Logger logger = LoggerFactory.getLogger(ListenerDeTransacao.class);

    private final EntityManager entityManager;

    public ListenerDeTransacao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    @Transactional
    public void escutar(EventoTransacaoListener eventoTransacaoListener) { //1
        logger.info("Evento {} recebido com sucesso!", eventoTransacaoListener.getId());
        Compra compra = eventoTransacaoListener.toModel(); //1
        logger.info("Evento {} convertido com sucesso!", eventoTransacaoListener.getId());

        TypedQuery<Fatura> query = entityManager.createQuery("select u from " + Fatura.class.getName() + " u ", Fatura.class); //1
        List<Fatura> faturas = query.getResultList();

        final Fatura fatura = new Fatura(new ArrayList<>());

        faturas.forEach(faturaDaLista -> { //1
            if (faturaDaLista.getCompras().get(0).getCartao().getIdCartao().equals(compra.getCartao().getIdCartao())){ //1
                fatura.setId(faturaDaLista.getId());
                fatura.setCompras(faturaDaLista.getCompras());
            }
        });

        if (fatura.getId() == null) { //1
            entityManager.persist(fatura);
        } else { //1
            entityManager.merge(fatura);
        }

        logger.info("Evento {} da fatura id={} persistido com sucesso!", eventoTransacaoListener.getId(), fatura.getId());
    }
}
