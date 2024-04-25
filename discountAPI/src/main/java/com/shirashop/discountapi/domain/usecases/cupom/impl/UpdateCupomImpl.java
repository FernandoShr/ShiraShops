package com.shirashop.discountapi.domain.usecases.cupom.impl;

import com.shirashop.discountapi.domain.gateway.CupomGateway;
import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.domain.usecases.cupom.interfaces.UpdateCupom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateCupomImpl implements UpdateCupom {

    private final CupomGateway cupomGateway;

    @Override
    public CupomModel update(CupomModel cupomModel) {
        return cupomGateway.updateCupomGateway(cupomModel);
    }
}