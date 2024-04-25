package com.shirashop.discountapi.domain.usecases.cupom.interfaces;

import com.shirashop.discountapi.domain.model.CupomModel;

public interface DeactivateCupom {

    CupomModel deactivate (Long cupomId);

}
