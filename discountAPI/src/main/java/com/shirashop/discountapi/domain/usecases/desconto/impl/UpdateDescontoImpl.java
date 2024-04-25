package com.shirashop.discountapi.domain.usecases.desconto.impl;

import com.shirashop.discountapi.domain.gateway.DescontoGateway;
import com.shirashop.discountapi.domain.model.DescontoModel;
import com.shirashop.discountapi.domain.usecases.desconto.interfaces.UpdateDesconto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateDescontoImpl implements UpdateDesconto {

    private final DescontoGateway descontoGateway;

    @Override
    public DescontoModel update(DescontoModel descontoModel) {
        return descontoGateway.updateDescontoGateway(descontoModel);
    }
}
