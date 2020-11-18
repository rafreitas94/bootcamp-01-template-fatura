package br.com.itau.fatura.client;

import br.com.itau.fatura.exception.ApiErrorException;
import br.com.itau.fatura.model.Parcela;
import br.com.itau.fatura.model.ParcelaRequestClient;
import br.com.itau.fatura.model.solicitacao.ParcelaResponseClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ParcelaClient {

    private final Logger logger = LoggerFactory.getLogger(ParcelaClient.class);

    @Value(value = "${endereco.parcela.client}")
    private String url;

    public ParcelaResponseClient notificaParcela(String idPFatura, Parcela parcela, String numeroCartao) { //1
        try { //1 1
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<ParcelaRequestClient> requestClient = new HttpEntity<>(new ParcelaRequestClient(idPFatura, parcela.getQuantidadeParcelas(), parcela.getValorParcela()));
            return restTemplate.postForObject(url + numeroCartao + "/parcelas", requestClient, ParcelaResponseClient.class);
        } catch (HttpClientErrorException | HttpServerErrorException httpErrorException) { //1 1
            logger.info("Não foi possível notificar solicitação de parcelamento. Erro {}. Mensagem: {}", httpErrorException.getStatusCode().value(), httpErrorException.getMessage());
            throw new ApiErrorException(httpErrorException.getStatusCode(), "Não foi possível notificar solicitação de parcelamento. Erro: " + httpErrorException.getMessage());
        }
    }
}
