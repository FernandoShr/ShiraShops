package com.shirashop.discountapi.app.service.desconto;

import com.shirashop.discountapi.app.dto.desconto.DescontoRequest;
import com.shirashop.discountapi.app.dto.desconto.DescontoResponse;
import com.shirashop.discountapi.app.mapper.MapperAppDesconto;
import com.shirashop.discountapi.cross.enums.TipoDesconto;
import com.shirashop.discountapi.cross.feignclients.ProductFeignClient;
import com.shirashop.discountapi.domain.usecases.desconto.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("DescontoServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class DescontoServiceImpl implements DescontoService{

    private final MapperAppDesconto descontoMapper;

    private final ProductFeignClient productFeignClient;

    // Usecases
    private final FindDescontoById findDescontoById;
    private final FindAllDescontoByTipoDesconto findAllDescontoByTipoDesconto;
    private final SaveDesconto saveDesconto;
    private final UpdateDesconto updateDesconto;
    private final ReactivateDesconto reactivateDesconto;
    private final DeactivateDesconto deactivateDesconto;

    @Override
    public DescontoResponse findDescontoByIdService(Long descontoId) {
        return descontoMapper.modelToResponse(
                findDescontoById.findById(descontoId));
    }

    @Override
    public List<DescontoResponse> findAllDescontoByTipoDescontoService(TipoDesconto tipoDesconto) {
        return descontoMapper.modelToResponseList(
                findAllDescontoByTipoDesconto.findAllByTipoDesconto(tipoDesconto));
    }

    @Override
    public DescontoResponse saveDescontoService(DescontoRequest descontoRequest) {

        // Verifica se existe o objeto à receber o desconto
        switch (descontoRequest.tipoDesconto()) {
            case PROD ->
                    productFeignClient.findProdById(descontoRequest.idObjetoDoDesconto());
            case CAT ->
                    productFeignClient.findCatById(descontoRequest.idObjetoDoDesconto());
            case SUBCAT ->
                    productFeignClient.findSubcatById(descontoRequest.idObjetoDoDesconto());
        }

        return descontoMapper.modelToResponse(
                saveDesconto.save(
                        descontoMapper.requestToModelCreate(descontoRequest)));
    }

    @Override
    public DescontoResponse updateDescontoService(DescontoRequest descontoRequest) {
        return descontoMapper.modelToResponse(
                updateDesconto.update(
                        descontoMapper.requestToModel(descontoRequest)));
    }

    @Override
    public DescontoResponse reactivateDescontoService(Long descontoId) {
        return descontoMapper.modelToResponse(
                reactivateDesconto.reactivate(descontoId));
    }

    @Override
    public DescontoResponse deactivateDescontoService(Long descontoId) {
        return descontoMapper.modelToResponse(
                deactivateDesconto.deactivate(descontoId));
    }

}