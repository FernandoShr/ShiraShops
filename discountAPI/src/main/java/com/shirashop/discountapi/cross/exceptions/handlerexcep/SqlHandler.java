package com.shirashop.discountapi.cross.exceptions.handlerexcep;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class SqlHandler extends ResponseEntityExceptionHandler {

    /**
     * Exception Handler para caso encontre duplicatas no banco de dados
     * @param e Exceção capturada
     * @return ResponseEntity<Object> contendo uma mensagem para o usuário
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> violationConstraint (SQLIntegrityConstraintViolationException e){

        ResponseEntity<Object> response = null;

        if(e.getMessage().contains("desconto_entity")){

            response = ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .header("ErrorMessage", "Desconto de tipo e idObjeto duplicado")
                    .body("Já existe um desconto como esse cadastrado. (Mesmo Tipo e Objeto)");

        } else if (e.getMessage().contains("cupom_entity")) {

            response = ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .header("ErrorMessage", "Cupom de código duplicado")
                    .body("Já existe um cupom com esse código.");
        }
        // log imprimido automaticamente
        return response;
    }

}