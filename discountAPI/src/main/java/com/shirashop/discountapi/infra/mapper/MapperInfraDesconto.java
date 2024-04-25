package com.shirashop.discountapi.infra.mapper;

import com.shirashop.discountapi.domain.model.DescontoModel;
import com.shirashop.discountapi.infra.entity.DescontoEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperInfraDesconto {

    DescontoEntity modelToEntity (DescontoModel descontoModel);

    DescontoModel entityToModel (DescontoEntity descontoEntity);

    List<DescontoModel> entityToModelList(List<DescontoEntity> descontoEntityList);

    @Mapping(source = "descontoDB.idDesconto", target = "idDesconto")
    @Mapping(source = "descontoDB.dataCriacao", target = "dataCriacao")
    @Mapping(source = "descontoDB.isActive", target = "isActive")
    @Mapping(target = "dataExpiracao",
            expression = "java(descontoReceived.getDataExpiracao() == null ? descontoDB.getDataExpiracao() : descontoReceived.getDataExpiracao())")
    @Mapping(target = "idObjetoDoDesconto",
            expression = "java(descontoReceived.getIdObjetoDoDesconto() == null ? descontoDB.getIdObjetoDoDesconto() : descontoReceived.getIdObjetoDoDesconto())")
    @Mapping(target = "percentualDesconto",
            expression = "java(descontoReceived.getPercentualDesconto() == null ? descontoDB.getPercentualDesconto() : descontoReceived.getPercentualDesconto())")
    @Mapping(target = "tipoDesconto",
            expression = "java(descontoReceived.getTipoDesconto() == null ? descontoDB.getTipoDesconto() : descontoReceived.getTipoDesconto())")
    DescontoEntity modelToEntityUpdate(DescontoModel descontoReceived, DescontoModel descontoDB);
}
