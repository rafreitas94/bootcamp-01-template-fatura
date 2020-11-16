package br.com.itau.fatura.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class HandleAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> metodoInvalido(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<Object> listaDeErros = new ArrayList<>();
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        fieldErrors.forEach(fieldError -> {
            Map<String, String> erro = new HashMap<>();
            erro.put("mensagem", fieldError.getDefaultMessage());
            erro.put("campo", fieldError.getField());
            listaDeErros.add(erro);
        });

        ErroPadronizado erroPadronizado = new ErroPadronizado(HttpStatus.BAD_REQUEST, "Solicitacao Invalida", listaDeErros);

        return ResponseEntity.badRequest().body(erroPadronizado);
    }

    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<?> apiError(ApiErrorException apiErrorException) {
        List<Object> listaDeErros = new ArrayList<>();

        Map<String, String> mensagem = new HashMap<>();
        mensagem.put("erro", apiErrorException.getMensagem());

        listaDeErros.add(mensagem);

        ErroPadronizado erroPadronizado = new ErroPadronizado(apiErrorException.getHttpStatus(), "Erro na execucao da solicitacao", listaDeErros);

        return ResponseEntity.status(apiErrorException.getHttpStatus()).body(erroPadronizado);
    }
}
