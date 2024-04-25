package com.shirashop.discountapi.domain.usecases.desconto.impl;

import com.shirashop.discountapi.domain.gateway.DescontoGateway;
import com.shirashop.discountapi.domain.model.DescontoModel;
import com.shirashop.discountapi.domain.usecases.desconto.interfaces.DeactivateDesconto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeactivateDescontoImpl implements DeactivateDesconto {

    private final DescontoGateway descontoGateway;

    @Override
    public DescontoModel deactivate(Long descontoId) {
        return descontoGateway.deactivateDescontoGateway(descontoId);
    }
}
