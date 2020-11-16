package br.com.itau.fatura.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErroPadronizado {

    Map<String, Object> mapaDeMensagens = new HashMap<>();

    public ErroPadronizado(HttpStatus codigo, String mensagem, List<Object> campos) {
        this.mapaDeMensagens.put("codigo", codigo.value());
        this.mapaDeMensagens.put("mensagem", mensagem);
        this.mapaDeMensagens.put("campos", campos);
    }

    public Map<String, Object> getMensagens() {
        return mapaDeMensagens;
    }
}
