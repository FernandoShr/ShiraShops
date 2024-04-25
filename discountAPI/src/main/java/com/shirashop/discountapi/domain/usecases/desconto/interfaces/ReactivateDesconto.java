package com.shirashop.discountapi.domain.usecases.desconto.interfaces;

import com.shirashop.discountapi.domain.model.DescontoModel;

public interface ReactivateDesconto {

    DescontoModel reactivate (Long descontoId);
}
