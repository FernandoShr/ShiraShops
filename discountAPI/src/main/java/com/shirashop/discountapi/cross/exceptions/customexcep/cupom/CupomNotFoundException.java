package com.shirashop.discountapi.cross.exceptions.customexcep.cupom;

import lombok.Getter;

@Getter
public class CupomNotFoundException extends RuntimeException {

    private String logMsg;
    public CupomNotFoundException(String userMsg, String logMsg){
        super(userMsg);
        this.logMsg = logMsg;
    }
}