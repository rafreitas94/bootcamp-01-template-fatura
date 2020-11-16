package br.com.itau.fatura.service;

import br.com.itau.fatura.model.Cartao;
import br.com.itau.fatura.model.Fatura;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FaturaService {

    private final Logger logger = LoggerFactory.getLogger(FaturaService.class);

    private final EntityManager entityManager;

    public FaturaService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Fatura> buscaFatura(String numeroCartao) { //1
        Query buscaComprasParaAqueleCartao = entityManager.createQuery("select u from " + Cartao.class.getName() + " u where u.idCartao =: value");
        buscaComprasParaAqueleCartao.setParameter("value", numeroCartao);

        if (buscaComprasParaAqueleCartao.getResultList().isEmpty()) { //1
            logger.error("Cartão {} não encontrado", numeroCartao.substring(24));
            return null;
        }

        List<Cartao> cartoes = buscaComprasParaAqueleCartao.getResultList(); //1
        logger.info("{} compras encontradas para o cartão com final {}", cartoes.size(), numeroCartao.substring(24));

        List<Fatura> compras = new ArrayList<>();

        cartoes.forEach(cartao -> { //1
            Query buscaComposicaoDasCompras = entityManager.createQuery("select u from " + Fatura.class.getName() + " u where u.cartao =: value");
            buscaComposicaoDasCompras.setParameter("value", cartao);

            Fatura detalheCompra = (Fatura) buscaComposicaoDasCompras.getResultList().get(0);

            compras.add(detalheCompra);
        });

        return compras;
    }

    public List<Fatura> filtraFaturaPorUltimasCompras(List<Fatura> compras) {
        List<Fatura> listaDeComprasComFiltro = new ArrayList<>();

        if (compras.size() > 10) { //1
            for (int i = compras.size(); i > (compras.size() - 10); i--) { //1
                listaDeComprasComFiltro.add(compras.get(i - 1));
            }
            compras = listaDeComprasComFiltro;
        }

        return compras;
    }

    public BigDecimal calculaValorTotalUtilizado(List<Fatura> compras) {
        BigDecimal valorTotalUtilizado = new BigDecimal(0);
        for (Fatura detalhamentoCompra : compras) { //1
            valorTotalUtilizado = valorTotalUtilizado.add(detalhamentoCompra.getValor());
        }
        return valorTotalUtilizado;
    }
}
