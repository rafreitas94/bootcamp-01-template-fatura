package br.com.itau.fatura.service;

import br.com.itau.fatura.client.CartaoClient;
import br.com.itau.fatura.exception.ApiErrorException;
import br.com.itau.fatura.model.Fatura;
import br.com.itau.fatura.model.Renegociacao;
import br.com.itau.fatura.model.RenegociacaoRequestClient;
import br.com.itau.fatura.model.solicitacao.RenegociacaoResponseClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RenegociacaoService {

    private final Logger logger = LoggerFactory.getLogger(RenegociacaoService.class);
//1
    private final CartaoClient cartaoClient;

    public RenegociacaoService(CartaoClient cartaoClient) {
        this.cartaoClient = cartaoClient;
    }

    public RenegociacaoResponseClient notificaRenegociacao(String numeroCartao, Fatura fatura, Renegociacao renegociacao) { //1 1
        try { //1
            return cartaoClient.notificaRenegociacao(numeroCartao, new RenegociacaoRequestClient(fatura.getId(), renegociacao.getQuantidadeParcelas(), renegociacao.getValorParcela()));
        } catch (FeignException feignException) { //1
            logger.error("Erro ao solicitar renegociação: {}", feignException.getMessage());
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Nao foi possivel solicitar a renegociacao"); //1
        }
    }
}
