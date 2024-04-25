package com.shirashop.discountapi.domain.usecases.cupom.impl;

import com.shirashop.discountapi.domain.gateway.CupomGateway;
import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.domain.usecases.cupom.interfaces.DeactivateCupom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeactivateCupomImpl implements DeactivateCupom {

    private final CupomGateway cupomGateway;

    @Override
    public CupomModel deactivate(Long cupomId) {
        return cupomGateway.deactivateCupomGateway(cupomId);
    }
}
