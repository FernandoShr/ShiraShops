package com.shirashop.discountapi.app.mapper;

import com.shirashop.discountapi.app.dto.cupom.CupomRequest;
import com.shirashop.discountapi.app.dto.cupom.CupomResponse;
import com.shirashop.discountapi.domain.model.CupomModel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperAppCupom {

    @Mapping(target = "dataCriacao",expression = "java(LocalDate.now())")
    @Mapping(target = "ativo", expression = "java(true)")
    CupomModel requestToModelCreate (CupomRequest cupomRequest);

    CupomModel requestToModel (CupomRequest cupomRequest);

    CupomResponse modelToResponse(CupomModel cupomModel);

    List<CupomResponse> modelToResponseList(List<CupomModel> cupomModelList);
}
