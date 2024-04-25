package com.shirashop.discountapi.domain.usecases.cupom.impl;

import com.shirashop.discountapi.domain.gateway.CupomGateway;
import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.domain.usecases.cupom.interfaces.FindAllCupomByAtivo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllCupomByAtivoImpl implements FindAllCupomByAtivo {

    private final CupomGateway cupomGateway;

    @Override
    public List<CupomModel> findAllByAtivo(Boolean isActive) {
        return cupomGateway.findAllCupomByAtivoGateway(isActive);
    }
}
