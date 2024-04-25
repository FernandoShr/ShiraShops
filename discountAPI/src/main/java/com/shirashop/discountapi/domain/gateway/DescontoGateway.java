package com.shirashop.discountapi.domain.gateway;

import com.shirashop.discountapi.cross.enums.TipoDesconto;
import com.shirashop.discountapi.domain.model.DescontoModel;

import java.util.List;

public interface DescontoGateway {

    DescontoModel findDescontoByIdGateway (Long descontoId);
    List<DescontoModel> findAllDescontoByTipoDescontoGateway (TipoDesconto tipoDesconto);
    DescontoModel saveDescontoGateway (DescontoModel descontoModel);
    DescontoModel updateDescontoGateway (DescontoModel descontoModel);
    DescontoModel reactivateDescontoGateway (Long descontoId);
    DescontoModel deactivateDescontoGateway (Long descontoId);

}
