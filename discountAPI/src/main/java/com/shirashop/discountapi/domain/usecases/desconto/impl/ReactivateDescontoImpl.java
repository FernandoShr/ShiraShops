package com.shirashop.discountapi.domain.usecases.desconto.impl;

import com.shirashop.discountapi.domain.gateway.DescontoGateway;
import com.shirashop.discountapi.domain.model.DescontoModel;
import com.shirashop.discountapi.domain.usecases.desconto.interfaces.ReactivateDesconto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReactivateDescontoImpl implements ReactivateDesconto {

    private final DescontoGateway descontoGateway;

    @Override
    public DescontoModel reactivate(Long descontoId) {
        return descontoGateway.reactivateDescontoGateway(descontoId);
    }
}
