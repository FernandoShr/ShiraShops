package com.shirashop.discountapi.app.mapper;

import com.shirashop.discountapi.app.dto.desconto.DescontoRequest;
import com.shirashop.discountapi.app.dto.desconto.DescontoResponse;
import com.shirashop.discountapi.domain.model.DescontoModel;
import org.mapstruct.*;

import java.util.List;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperAppDesconto {

    DescontoResponse modelToResponse(DescontoModel descontoModel);

    @Mapping(target = "dataCriacao",expression = "java(LocalDate.now())")
    @Mapping(target = "isActive", expression = "java(true)")
    DescontoModel requestToModelCreate(DescontoRequest descontoRequest);

    DescontoModel requestToModel(DescontoRequest descontoRequest);

    List<DescontoResponse> modelToResponseList (List<DescontoModel> descontoModelList);

}
