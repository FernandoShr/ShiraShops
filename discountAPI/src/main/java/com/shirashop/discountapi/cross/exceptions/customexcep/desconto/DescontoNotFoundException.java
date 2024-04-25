package com.shirashop.discountapi.cross.exceptions.customexcep.desconto;

import lombok.Getter;

@Getter
public class DescontoNotFoundException extends RuntimeException{

    private String logMsg;
    public DescontoNotFoundException (String userMsg, String logMsg){
        super(userMsg);
        this.logMsg = logMsg;
    }
}