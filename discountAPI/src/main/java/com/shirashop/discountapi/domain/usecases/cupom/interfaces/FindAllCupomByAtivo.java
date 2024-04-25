package com.shirashop.discountapi.domain.usecases.cupom.interfaces;

import com.shirashop.discountapi.domain.model.CupomModel;

import java.util.List;

public interface FindAllCupomByAtivo {

    List<CupomModel> findAllByAtivo(Boolean isActive);

}
