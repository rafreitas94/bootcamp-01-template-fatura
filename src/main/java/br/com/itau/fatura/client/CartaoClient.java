package br.com.itau.fatura.client;

import br.com.itau.fatura.model.solicitacao.CartaoResponseClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cartao", url = "http://localhost:8888")
public interface CartaoClient {

    @GetMapping("/api/cartoes/{id}")
    CartaoResponseClient buscaCartao(@PathVariable("id") String numeroCartao);
}
