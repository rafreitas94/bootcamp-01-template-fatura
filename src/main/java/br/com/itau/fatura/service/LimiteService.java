package br.com.itau.fatura.service;

import br.com.itau.fatura.client.CartaoClient;
import br.com.itau.fatura.exception.ApiErrorException;
import br.com.itau.fatura.model.solicitacao.CartaoResponseClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LimiteService {

    private final Logger logger = LoggerFactory.getLogger(LimiteService.class);
//1
    private final CartaoClient cartaoClient;

    public LimiteService(CartaoClient cartaoClient) {
        this.cartaoClient = cartaoClient;
    }

    public BigDecimal buscarValorDoLimite(String numeroCartao) { //1
        CartaoResponseClient cartaoResponseClient;
        BigDecimal valorDoLimite = new BigDecimal(0);

        try { //1
            cartaoResponseClient = cartaoClient.buscaCartao(numeroCartao);
            return valorDoLimite.add(cartaoResponseClient.getLimite());
        } catch (FeignException feignException) { //1 1
            logger.error("Erro ao buscar cartao: {}", feignException.getMessage());
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Nao foi possivel buscar o cartao"); //1
        }
    }
}
