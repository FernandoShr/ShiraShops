package com.shirashop.discountapi.domain.usecases.cupom.impl;

import com.shirashop.discountapi.domain.gateway.CupomGateway;
import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.domain.usecases.cupom.interfaces.SaveCupom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveCupomImpl implements SaveCupom {

    private final CupomGateway cupomGateway;

    @Override
    public CupomModel save(CupomModel cupomModel) {
        return cupomGateway.saveCupomGateway(cupomModel);
    }
}
