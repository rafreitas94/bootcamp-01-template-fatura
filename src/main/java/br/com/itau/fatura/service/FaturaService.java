package br.com.itau.fatura.service;

import br.com.itau.fatura.model.Compra;
import br.com.itau.fatura.model.Fatura;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    public Fatura buscaFatura(String numeroCartao) { //1
        TypedQuery<Fatura> query = entityManager.createQuery("select u from " + Fatura.class.getName() + " u ", Fatura.class);
        List<Fatura> faturas = query.getResultList();

        final Fatura faturaEncontrada = new Fatura(new ArrayList<>());

        faturas.forEach(fatura -> { //1
            if (fatura.getCompras().get(0).getCartao().getIdCartao().equals(numeroCartao)){ //1
                faturaEncontrada.setId(fatura.getId());
                faturaEncontrada.setCompras(fatura.getCompras());
            }
        });

        return faturaEncontrada;
    }

    public Fatura filtraFaturaPorUltimasCompras(Fatura fatura) {
        Fatura faturaComFiltroDeCompras = new Fatura(new ArrayList<>());

        if (fatura.getCompras().size() > 10) { //1
            for (int i = fatura.getCompras().size(); i > (fatura.getCompras().size() - 10); i--) { //1
                faturaComFiltroDeCompras.getCompras().add(fatura.getCompras().get(i - 1));
            }
            fatura = faturaComFiltroDeCompras;
        }

        return fatura;
    }

    public BigDecimal calculaValorTotalUtilizado(List<Compra> compras) {
        BigDecimal valorTotalUtilizado = new BigDecimal(0);
        for (Compra detalhamentoCompra : compras) { //1
            valorTotalUtilizado = valorTotalUtilizado.add(detalhamentoCompra.getValor());
        }
        return valorTotalUtilizado;
    }
}
