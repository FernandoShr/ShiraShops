package com.shirashop.discountapi.domain.gateway;

import com.shirashop.discountapi.domain.model.CupomModel;

import java.util.List;

public interface CupomGateway {

    CupomModel findCupomByIdGateway (Long cupomId);
    CupomModel findCupomByCodigoGateway (String cupomCodigo);
    List<CupomModel> findAllCupomByAtivoGateway (Boolean isActive);
    CupomModel saveCupomGateway (CupomModel cupomReceived);
    CupomModel updateCupomGateway (CupomModel cupomReceived);
    CupomModel reactivateCupomGateway (Long cupomId);
    CupomModel deactivateCupomGateway (Long cupomId);
}
