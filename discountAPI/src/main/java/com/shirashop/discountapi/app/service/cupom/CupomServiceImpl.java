package com.shirashop.discountapi.app.service.cupom;

import com.shirashop.discountapi.app.dto.cupom.CupomRequest;
import com.shirashop.discountapi.app.dto.cupom.CupomResponse;
import com.shirashop.discountapi.app.mapper.MapperAppCupom;
import com.shirashop.discountapi.domain.usecases.cupom.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CupomServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class CupomServiceImpl implements CupomService {

    private final MapperAppCupom cupomMapper;

    // Usecases
    private final FindCupomById findCupomById;
    private final FindCupomByCodigo findCupomByCodigo;
    private final FindAllCupomByAtivo findAllCupomByAtivo;
    private final SaveCupom saveCupom;
    private final UpdateCupom updateCupom;
    private final ReactivateCupom reactivateCupom;
    private final DeactivateCupom deactivateCupom;

    @Override
    public CupomResponse findCupomByIdService(Long cupomId) {
        return cupomMapper.modelToResponse(
                findCupomById.findById(cupomId));
    }

    @Override
    public CupomResponse findCupomByCodigoService(String cupomCodigo) {
        return cupomMapper.modelToResponse(
                findCupomByCodigo.findByCodigo(cupomCodigo));
    }

    @Override
    public List<CupomResponse> findAllCupomByAtivoService(Boolean isActive) {
        return cupomMapper.modelToResponseList(
                findAllCupomByAtivo.findAllByAtivo(isActive));
    }

    @Override
    public CupomResponse saveCupomService(CupomRequest cupomRequest) {
        // Chamando o usecase com CupomRequest convertido para CupomModel
        return cupomMapper.modelToResponse(
                saveCupom.save(
                        cupomMapper.requestToModelCreate(cupomRequest)));
    }

    @Override
    public CupomResponse updateCupomService(CupomRequest cupomRequest) {
        return cupomMapper.modelToResponse(
                updateCupom.update(
                        cupomMapper.requestToModel(cupomRequest)));

    }

    @Override
    public CupomResponse reactivateCupomService(Long cupomId) {
        return cupomMapper.modelToResponse(
                reactivateCupom.reactivate(cupomId));
    }

    @Override
    public CupomResponse deactivateCupomService(Long cupomId) {
        return cupomMapper.modelToResponse(
                deactivateCupom.deactivate(cupomId));
    }
}
