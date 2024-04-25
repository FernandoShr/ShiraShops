package com.shirashop.discountapi.domain.usecases.desconto.impl;

import com.shirashop.discountapi.domain.gateway.DescontoGateway;
import com.shirashop.discountapi.domain.model.DescontoModel;
import com.shirashop.discountapi.domain.usecases.desconto.interfaces.SaveDesconto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveDescontoImpl implements SaveDesconto {

    private final DescontoGateway descontoGateway;

    @Override
    public DescontoModel save(DescontoModel descontoModel) {
        return descontoGateway.saveDescontoGateway(descontoModel);
    }
}
