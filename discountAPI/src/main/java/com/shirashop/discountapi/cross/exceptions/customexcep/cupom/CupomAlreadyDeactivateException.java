package com.shirashop.discountapi.cross.exceptions.customexcep.cupom;

import lombok.Getter;

@Getter
public class CupomAlreadyDeactivateException extends RuntimeException{

    private String logMsg;
    public CupomAlreadyDeactivateException (String userMsg, String logMsg){
        super(userMsg);
        this.logMsg = logMsg;
    }

}
