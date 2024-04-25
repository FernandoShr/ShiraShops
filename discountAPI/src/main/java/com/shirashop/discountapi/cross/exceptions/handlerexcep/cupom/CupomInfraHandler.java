package com.shirashop.discountapi.cross.exceptions.handlerexcep.cupom;

import com.shirashop.discountapi.cross.exceptions.customexcep.cupom.CupomAlreadyActiveException;
import com.shirashop.discountapi.cross.exceptions.customexcep.cupom.CupomAlreadyDeactivateException;
import com.shirashop.discountapi.cross.exceptions.customexcep.cupom.CupomNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class CupomInfraHandler extends ResponseEntityExceptionHandler {
    /**
     * Exception Handler para caso não encontre um cupom em alguma pesquisa no banco de dados
     * @param e Exception capturada
     * @return ResponseEntity<Object> contendo uma mensagem para o usuário
     */
    @ExceptionHandler(CupomNotFoundException.class)
    public ResponseEntity<Object> cupomNotFound (CupomNotFoundException e) {
        log.error(e.getLogMsg());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .header("ErrorMessage", "Cupom não encontrado")
                .body(e.getMessage());
    }
    @ExceptionHandler(CupomAlreadyActiveException.class)
    public ResponseEntity<Object> cupomAlreadyActive (CupomAlreadyActiveException e){
        log.error(e.getLogMsg());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .header("ErrorMessage", "Cupom já ativado")
                .body(e.getMessage());
    }

    @ExceptionHandler(CupomAlreadyDeactivateException.class)
    public ResponseEntity<Object> cupomAlreadyDeactivate (CupomAlreadyDeactivateException e){
        log.error(e.getLogMsg());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .header("ErrorMessage", "Cupom já desativado")
                .body(e.getMessage());
    }

}