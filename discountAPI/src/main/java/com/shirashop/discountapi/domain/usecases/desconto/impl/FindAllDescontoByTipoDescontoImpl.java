package com.shirashop.discountapi.domain.usecases.desconto.impl;

import com.shirashop.discountapi.cross.enums.TipoDesconto;
import com.shirashop.discountapi.domain.gateway.DescontoGateway;
import com.shirashop.discountapi.domain.model.DescontoModel;
import com.shirashop.discountapi.domain.usecases.desconto.interfaces.FindAllDescontoByTipoDesconto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllDescontoByTipoDescontoImpl implements FindAllDescontoByTipoDesconto {

    private final DescontoGateway descontoGateway;

    @Override
    public List<DescontoModel> findAllByTipoDesconto(TipoDesconto tipoDesconto) {
        return descontoGateway.findAllDescontoByTipoDescontoGateway(tipoDesconto);
    }
}
