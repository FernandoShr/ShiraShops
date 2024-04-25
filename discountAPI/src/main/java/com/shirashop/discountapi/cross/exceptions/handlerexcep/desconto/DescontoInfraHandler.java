package com.shirashop.discountapi.cross.exceptions.handlerexcep.desconto;

import com.shirashop.discountapi.cross.exceptions.customexcep.desconto.DescontoAlreadyActiveException;
import com.shirashop.discountapi.cross.exceptions.customexcep.desconto.DescontoAlreadyDeactivateException;
import com.shirashop.discountapi.cross.exceptions.customexcep.desconto.DescontoNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class DescontoInfraHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DescontoNotFoundException.class)
    public ResponseEntity<Object> descontoNotFound (DescontoNotFoundException e){
        log.error(e.getLogMsg());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .header("ErrorMessage:", "Desconto não encontrado!")
                .body(e.getMessage());
    }

    @ExceptionHandler(DescontoAlreadyActiveException.class)
    public ResponseEntity<Object> descontoAlreadyActive (DescontoAlreadyActiveException e){
        log.error(e.getLogMsg());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .header("ErrorMessage", "Desconto já está ativo!")
                .body(e.getMessage());
    }

    @ExceptionHandler(DescontoAlreadyDeactivateException.class)
    public ResponseEntity<Object> descontoAlreadyDeactivate (DescontoAlreadyDeactivateException e){
        log.error(e.getLogMsg());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .header("ErrorMessage", "Desconto já está inativo!")
                .body(e.getMessage());
    }

}