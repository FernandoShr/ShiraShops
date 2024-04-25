package com.shirashop.discountapi.domain.usecases.cupom.impl;

import com.shirashop.discountapi.domain.gateway.CupomGateway;
import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.domain.usecases.cupom.interfaces.ReactivateCupom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReactivateCupomImpl implements ReactivateCupom {

    private final CupomGateway cupomGateway;

    @Override
    public CupomModel reactivate(Long cupomId) {
        return cupomGateway.reactivateCupomGateway(cupomId);
    }
}
