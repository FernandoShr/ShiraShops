package com.shirashop.discountapi.cross.exceptions.customexcep.cupom;

import lombok.Getter;

@Getter
public class CupomAlreadyActiveException extends RuntimeException{

    private String logMsg;
    public CupomAlreadyActiveException (String userMsg, String logMsg){
        super(userMsg);
        this.logMsg = logMsg;
    }
}
