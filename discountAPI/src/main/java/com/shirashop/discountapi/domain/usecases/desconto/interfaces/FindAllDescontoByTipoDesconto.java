package com.shirashop.discountapi.domain.usecases.desconto.interfaces;

import com.shirashop.discountapi.cross.enums.TipoDesconto;
import com.shirashop.discountapi.domain.model.DescontoModel;

import java.util.List;

public interface FindAllDescontoByTipoDesconto {

    List<DescontoModel> findAllByTipoDesconto (TipoDesconto tipoDesconto);
}
