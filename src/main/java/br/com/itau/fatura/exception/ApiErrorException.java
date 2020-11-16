package br.com.itau.fatura.exception;

import org.springframework.http.HttpStatus;

public class ApiErrorException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String mensagem;

    public ApiErrorException(HttpStatus unprocessableEntity, String mensagem) {
        this.httpStatus = unprocessableEntity;
        this.mensagem = mensagem;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMensagem() {
        return mensagem;
    }
}
