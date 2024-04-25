package com.shirashop.discountapi.domain.usecases.desconto.impl;

import com.shirashop.discountapi.domain.gateway.DescontoGateway;
import com.shirashop.discountapi.domain.model.DescontoModel;
import com.shirashop.discountapi.domain.usecases.desconto.interfaces.FindDescontoById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindDescontoByIdImpl implements FindDescontoById {

    private final DescontoGateway descontoGateway;

    @Override
    public DescontoModel findById(Long descontoId) {
        return descontoGateway.findDescontoByIdGateway(descontoId);
    }
}
