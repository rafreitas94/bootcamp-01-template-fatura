package br.com.itau.fatura.service;

import br.com.itau.fatura.model.Fatura;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Service
public class FaturaService {

    private final Logger logger = LoggerFactory.getLogger(FaturaService.class);

    private final EntityManager entityManager;

    public FaturaService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Fatura buscaFatura(String numeroCartao) { //1
        TypedQuery<Fatura> query = entityManager.createQuery("select u from " + Fatura.class.getName() + " u ", Fatura.class);
        List<Fatura> faturas = query.getResultList();

        final Fatura faturaEncontrada = new Fatura(new ArrayList<>(), new ArrayList<>());

        faturas.forEach(fatura -> { //1
            if (fatura.verificaComprasVazia(numeroCartao)) { //1
                    faturaEncontrada.setId(fatura.getId());
                    faturaEncontrada.setCompras(fatura.getCompras());
                    faturaEncontrada.setParcela(fatura.getParcela());
            }

            if (fatura.verificaRenegociacaoVazia(numeroCartao)) { //1
                faturaEncontrada.setRenegociacao(fatura.getRenegociacao());
            }
        });

        return faturaEncontrada;
    }

    public Fatura filtraFaturaPorUltimasCompras(Fatura fatura) {
        Fatura faturaComFiltroDeCompras = new Fatura(new ArrayList<>(), new ArrayList<>());

        if (fatura.quantidadeDeCompras() > 10) { //1
            for (int i = fatura.quantidadeDeCompras(); i > (fatura.quantidadeDeCompras() - 10); i--) { //1
                faturaComFiltroDeCompras.getCompras().add(fatura.getCompras().get(i - 1));
            }
            fatura = faturaComFiltroDeCompras;
        }

        return fatura;
    }
}
