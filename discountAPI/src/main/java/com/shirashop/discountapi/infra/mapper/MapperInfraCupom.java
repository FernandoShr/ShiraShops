package com.shirashop.discountapi.infra.mapper;

import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.infra.entity.CupomEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperInfraCupom {

    CupomModel entityToModel(CupomEntity cupomEntity);
    CupomEntity modelToEntity(CupomModel cupomModel);
    List<CupomModel> entityToModelList(List<CupomEntity> cupomEntityList);
    @Mapping(source = "cupomDB.dataCriacao", target = "dataCriacao")
    @Mapping(source = "cupomDB.ativo", target = "ativo")
    @Mapping(source = "cupomDB.id", target = "id")
    @Mapping(source = "cupomDB.codigo", target = "codigo")
    @Mapping(target = "dataExpiracao",
            expression = "java(cupomReceived.getDataExpiracao() == null ? cupomDB.getDataExpiracao() : cupomReceived.getDataExpiracao())")
    @Mapping(target = "percentual",
            expression = "java(cupomReceived.getPercentual() == null ? cupomDB.getPercentual() : cupomReceived.getPercentual())")
    @Mapping(source = "cupomReceived.descontoMaximo", target = "descontoMaximo")
    @Mapping(source = "cupomReceived.qtdMinimaProdutos", target = "qtdMinimaProdutos")
    @Mapping(source = "cupomReceived.valorMinimoPedido", target = "valorMinimoPedido")
    CupomEntity modelToEntityUpdate(CupomModel cupomReceived,CupomModel cupomDB);

}
