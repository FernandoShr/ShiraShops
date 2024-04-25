package com.shirashop.discountapi.domain.usecases.cupom.impl;

import com.shirashop.discountapi.domain.gateway.CupomGateway;
import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.domain.usecases.cupom.interfaces.FindCupomById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindCupomByIdImpl implements FindCupomById {

    private final CupomGateway cupomGateway;

    @Override
    public CupomModel findById(Long cupomId) {
        return cupomGateway.findCupomByIdGateway(cupomId);
    }
}