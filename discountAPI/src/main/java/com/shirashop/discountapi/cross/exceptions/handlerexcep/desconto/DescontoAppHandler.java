package com.shirashop.discountapi.cross.exceptions.handlerexcep.desconto;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class DescontoAppHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // Reunindo todos os erros de validações em um Map
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField(); // Resgata nome do campo
            String errorMessage = error.getDefaultMessage(); // Resgata mensagem do erro
            errors.put(fieldName, errorMessage);
        });

        log.error("Fields Validation error: " + errors);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header("ErrorMessage", "Campos não foram preenchidos corretamente")
                .body(errors);
    }

    @ExceptionHandler(FeignException.FeignClientException.class)
    public ResponseEntity<Object> feignClientNotFound(FeignException.FeignClientException e){
        log.error("Feign Client Not Found: " + "'"+ e.contentUTF8()+ "'");

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .header("ErrorMessage","Consulta Externa não concluída!")
                .body(e.contentUTF8());

    }
}