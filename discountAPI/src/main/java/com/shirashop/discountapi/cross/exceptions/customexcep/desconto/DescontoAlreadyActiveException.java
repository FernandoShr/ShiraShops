package com.shirashop.discountapi.cross.exceptions.customexcep.desconto;

import lombok.Getter;

@Getter
public class DescontoAlreadyActiveException extends RuntimeException{

    private String logMsg;
    public DescontoAlreadyActiveException (String userMsg, String logMsg){
        super(userMsg);
        this.logMsg = logMsg;
    }
}