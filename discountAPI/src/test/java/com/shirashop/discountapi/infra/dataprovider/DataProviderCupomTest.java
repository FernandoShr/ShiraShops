package com.shirashop.discountapi.infra.dataprovider;

import com.shirashop.discountapi.cross.exceptions.customexcep.cupom.CupomNotFoundException;
import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.infra.entity.CupomEntity;
import com.shirashop.discountapi.infra.mapper.MapperInfraCupom;
import com.shirashop.discountapi.infra.repository.CupomRepository;
import com.shirashop.discountapi.testObjects.CupomTestObjects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class DataProviderCupomTest {
    @Mock
    private CupomRepository cupomRepository;
    @Mock
    private MapperInfraCupom mapperInfra;
    @InjectMocks
    private DataProviderCupom dataProviderCupom;

    @Test
    void findCupomByIdGatewayTest_WithValidData(){
        when(cupomRepository.findById(anyLong())).thenReturn(Optional.of(CupomTestObjects.CUPOM_ENTITY));
        given(mapperInfra.entityToModel(CupomTestObjects.CUPOM_ENTITY)).willReturn(CupomTestObjects.CUPOM_MODEL);

        CupomModel sut = dataProviderCupom.findCupomByIdGateway(CupomTestObjects.CUPOM_MODEL.getId());

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_MODEL);
    }

    @Test
    void findCupomByIdGatewayTest_ThrownCupomNotFoundException(){

        assertThatThrownBy(
                ()->dataProviderCupom.findCupomByIdGateway(1L))
                .isInstanceOf(CupomNotFoundException.class);
    }

    @Test
    void findCupomByCodigoGatewayTest_SuccessEncounter(){
        when(cupomRepository.findByCodigo(anyString())).thenReturn(Optional.of(CupomTestObjects.CUPOM_ENTITY));
        given(mapperInfra.entityToModel(any(CupomEntity.class))).willReturn(CupomTestObjects.CUPOM_MODEL);

        CupomModel sut = dataProviderCupom.findCupomByCodigoGateway("abc");

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_MODEL);
    }

    @Test
    void findCupomByCodigoGatewayTest_ThrownCupomNotFoundException(){

        assertThatThrownBy(
                ()->dataProviderCupom.findCupomByCodigoGateway("1L"))
                .isInstanceOf(CupomNotFoundException.class);
    }

    @Test
    void findAllCupomByAtivoGatewayTest_SuccessCase(){
        when(cupomRepository.findAllByAtivo(anyBoolean())).thenReturn(CupomTestObjects.CUPOM_ENTITY_LIST);
        given(mapperInfra.entityToModelList(CupomTestObjects.CUPOM_ENTITY_LIST)).willReturn(CupomTestObjects.CUPOM_MODEL_LIST);

        List<CupomModel> allByAtivo = dataProviderCupom.findAllCupomByAtivoGateway(anyBoolean());

        assertThat(allByAtivo).isEqualTo(CupomTestObjects.CUPOM_MODEL_LIST);
    }

    @Test
    void saveCupomGatewayTest_SuccessCase() {
        given(mapperInfra.modelToEntity(CupomTestObjects.CUPOM_MODEL)).willReturn(CupomTestObjects.CUPOM_ENTITY);
        when(cupomRepository.save(CupomTestObjects.CUPOM_ENTITY)).thenReturn(CupomTestObjects.CUPOM_ENTITY);
        given(mapperInfra.entityToModel(CupomTestObjects.CUPOM_ENTITY)).willReturn(CupomTestObjects.CUPOM_MODEL);

        CupomModel sut = dataProviderCupom.saveCupomGateway(CupomTestObjects.CUPOM_MODEL);

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_MODEL);
    }

    @Test
    void updateCupomGatewayTest_SuccessCase(){
        when(cupomRepository.findByCodigo(anyString())).thenReturn(Optional.of(CupomTestObjects.CUPOM_ENTITY));
        given(mapperInfra.modelToEntityUpdate(any(), any())).willReturn(CupomTestObjects.CUPOM_ENTITY);
        when(cupomRepository.save(CupomTestObjects.CUPOM_ENTITY)).thenReturn(CupomTestObjects.CUPOM_ENTITY);
        given(mapperInfra.entityToModel(CupomTestObjects.CUPOM_ENTITY)).willReturn(CupomTestObjects.CUPOM_MODEL);

        CupomModel sut = dataProviderCupom.updateCupomGateway(CupomTestObjects.CUPOM_MODEL);

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_MODEL);
    }

    @Test
    void reactivateCupomGatewayTest_SuccessCase(){
        when(cupomRepository.findById(anyLong())).thenReturn(Optional.of(CupomTestObjects.CUPOM_ENTITY_TO_REACTIVATE));
        given(mapperInfra.modelToEntity(any())).willReturn(CupomTestObjects.CUPOM_ENTITY_TO_DEACTIVATE);
        when(cupomRepository.save(any())).thenReturn(CupomTestObjects.CUPOM_ENTITY_TO_DEACTIVATE);
        given(mapperInfra.entityToModel(CupomTestObjects.CUPOM_ENTITY_TO_REACTIVATE)).willReturn(CupomTestObjects.CUPOM_MODEL_TO_REACTIVATE);
        given(mapperInfra.entityToModel(CupomTestObjects.CUPOM_ENTITY_TO_DEACTIVATE)).willReturn(CupomTestObjects.CUPOM_MODEL_TO_DEACTIVATE);

        CupomModel sut = dataProviderCupom.reactivateCupomGateway(anyLong());

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_MODEL_TO_DEACTIVATE);
    }

    @Test
    void deactivateCupomGatewayTest_SuccessCase(){
        when(cupomRepository.findById(anyLong())).thenReturn(Optional.of(CupomTestObjects.CUPOM_ENTITY_TO_DEACTIVATE));
        given(mapperInfra.modelToEntity(any())).willReturn(CupomTestObjects.CUPOM_ENTITY_TO_REACTIVATE);
        when(cupomRepository.save(any())).thenReturn(CupomTestObjects.CUPOM_ENTITY_TO_REACTIVATE);
        given(mapperInfra.entityToModel(CupomTestObjects.CUPOM_ENTITY_TO_DEACTIVATE)).willReturn(CupomTestObjects.CUPOM_MODEL_TO_DEACTIVATE);
        given(mapperInfra.entityToModel(CupomTestObjects.CUPOM_ENTITY_TO_REACTIVATE)).willReturn(CupomTestObjects.CUPOM_MODEL_TO_REACTIVATE);

        CupomModel sut = dataProviderCupom.deactivateCupomGateway(anyLong());

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_MODEL_TO_REACTIVATE);
    }

}