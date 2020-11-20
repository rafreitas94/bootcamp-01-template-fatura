package br.com.itau.fatura.client;

import br.com.itau.fatura.model.RenegociacaoRequestClient;
import br.com.itau.fatura.model.solicitacao.CartaoResponseClient;
import br.com.itau.fatura.model.solicitacao.RenegociacaoResponseClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "cartao", url = "http://localhost:8888")
public interface CartaoClient {

    @GetMapping("/api/cartoes/{id}")
    CartaoResponseClient buscaCartao(@PathVariable("id") String numeroCartao);

    @PostMapping("/api/cartoes/{id}/renegociacoes")
    RenegociacaoResponseClient notificaRenegociacao(@PathVariable("id") String numeroCartao, @Valid @RequestBody RenegociacaoRequestClient requestClient);
}
