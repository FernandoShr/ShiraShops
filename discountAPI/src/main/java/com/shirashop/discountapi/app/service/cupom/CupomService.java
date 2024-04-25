package com.shirashop.discountapi.app.service.cupom;

import com.shirashop.discountapi.app.dto.cupom.CupomRequest;
import com.shirashop.discountapi.app.dto.cupom.CupomResponse;

import java.util.List;

public interface CupomService {

    CupomResponse findCupomByIdService (Long cupomId);
    CupomResponse findCupomByCodigoService (String cupomCodigo);
    List<CupomResponse> findAllCupomByAtivoService (Boolean isActive);
    CupomResponse saveCupomService (CupomRequest cupomRequest);
    CupomResponse updateCupomService (CupomRequest cupomRequest);
    CupomResponse reactivateCupomService (Long cupomId);
    CupomResponse deactivateCupomService (Long cupomId);
}