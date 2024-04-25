package com.shirashop.discountapi.app.service.desconto;

import com.shirashop.discountapi.app.dto.desconto.DescontoRequest;
import com.shirashop.discountapi.app.dto.desconto.DescontoResponse;
import com.shirashop.discountapi.cross.enums.TipoDesconto;

import java.util.List;

public interface DescontoService {

    DescontoResponse findDescontoByIdService (Long descontoId);
    List<DescontoResponse> findAllDescontoByTipoDescontoService (TipoDesconto tipoDesconto);
    DescontoResponse saveDescontoService (DescontoRequest descontoRequest);
    DescontoResponse updateDescontoService (DescontoRequest descontoRequest);
    DescontoResponse reactivateDescontoService (Long descontoId);
    DescontoResponse deactivateDescontoService (Long descontoId);
}
