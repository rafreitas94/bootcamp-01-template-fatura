package br.com.itau.fatura.service;

import br.com.itau.fatura.model.Cartao;
import br.com.itau.fatura.model.Fatura;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class FaturaService {

    private final Logger logger = LoggerFactory.getLogger(FaturaService.class);

    private final EntityManager entityManager;

    public FaturaService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Fatura> buscaCompras(String numeroCartao) {
        Query buscaComprasParaAqueleCartao = entityManager.createQuery("select u from " + Cartao.class.getName() + " u where u.idCartao =: value");
        buscaComprasParaAqueleCartao.setParameter("value", numeroCartao);

        if (buscaComprasParaAqueleCartao.getResultList().isEmpty()) { //1
            logger.error("Cartão não foi encontrado.");
            return null;
        }

        List<Cartao> cartoes = buscaComprasParaAqueleCartao.getResultList(); //1
        logger.info("{} compras encontradas para o cartão com final {}", cartoes.size(), numeroCartao.substring(24));

        List<Fatura> compras = new ArrayList<>(); //1

        cartoes.forEach(cartao -> { //1
            Query buscaComposicaoDasCompras = entityManager.createQuery("select u from " + Fatura.class.getName() + " u where u.cartao =: value");
            buscaComposicaoDasCompras.setParameter("value", cartao);

            Fatura detalheCompra = (Fatura) buscaComposicaoDasCompras.getResultList().get(0); //1

            compras.add(detalheCompra);
        });

        return compras;
    }
}
