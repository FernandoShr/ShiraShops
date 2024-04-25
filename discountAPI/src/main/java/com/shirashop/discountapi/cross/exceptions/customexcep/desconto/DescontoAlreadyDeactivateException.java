package com.shirashop.discountapi.cross.exceptions.customexcep.desconto;

import lombok.Getter;

@Getter
public class DescontoAlreadyDeactivateException extends RuntimeException{

    private String logMsg;
    public DescontoAlreadyDeactivateException (String userMsg, String logMsg){
        super(userMsg);
        this.logMsg = logMsg;
    }
}