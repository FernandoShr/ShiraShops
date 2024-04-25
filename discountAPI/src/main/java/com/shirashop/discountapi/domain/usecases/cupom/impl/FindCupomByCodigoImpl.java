package com.shirashop.discountapi.domain.usecases.cupom.impl;

import com.shirashop.discountapi.domain.gateway.CupomGateway;
import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.domain.usecases.cupom.interfaces.FindCupomByCodigo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindCupomByCodigoImpl implements FindCupomByCodigo {

    private final CupomGateway cupomGateway;

    @Override
    public CupomModel findByCodigo(String cupomCodigo) {
        return cupomGateway.findCupomByCodigoGateway(cupomCodigo);
    }
}