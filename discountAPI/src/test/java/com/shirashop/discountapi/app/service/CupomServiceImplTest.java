package com.shirashop.discountapi.app.service;

import com.shirashop.discountapi.app.dto.cupom.CupomResponse;
import com.shirashop.discountapi.app.mapper.MapperAppCupom;
import com.shirashop.discountapi.app.service.cupom.CupomServiceImpl;
import com.ibmshop.discountapi.domain.usecases.cupom.interfaces.*;
import com.shirashop.discountapi.domain.usecases.cupom.interfaces.*;
import com.shirashop.discountapi.testObjects.CupomTestObjects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CupomServiceImplTest {
    @Mock private MapperAppCupom mapperApp;
    @Mock private FindCupomById findCupomById;
    @Mock private FindCupomByCodigo findCupomByCodigo;
    @Mock private FindAllCupomByAtivo findAllCupomByAtivo;
    @Mock private SaveCupom saveCupom;
    @Mock private UpdateCupom updateCupom;
    @Mock private ReactivateCupom reactivateCupom;
    @Mock private DeactivateCupom deactivateCupom;
    @InjectMocks private CupomServiceImpl cupomService;

    @Test
    void findCupomByIdServiceTest_SuccessCase(){
        when(findCupomById.findById(anyLong())).thenReturn(CupomTestObjects.CUPOM_MODEL);
        given(mapperApp.modelToResponse(CupomTestObjects.CUPOM_MODEL)).willReturn(CupomTestObjects.CUPOM_RESPONSE_2);

        CupomResponse sut = cupomService.findCupomByIdService(anyLong());

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_RESPONSE_2);
    }
    @Test
    void findCupomByCodigoServiceTest_SuccessCase(){
        when(findCupomByCodigo.findByCodigo(anyString())).thenReturn(CupomTestObjects.CUPOM_MODEL);
        given(mapperApp.modelToResponse(CupomTestObjects.CUPOM_MODEL)).willReturn(CupomTestObjects.CUPOM_RESPONSE_2);

        CupomResponse sut = cupomService.findCupomByCodigoService(anyString());

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_RESPONSE_2);
    }
    @Test
    void findAllCupomByAtivoServiceTest_SuccessCase(){
        when(findAllCupomByAtivo.findAllByAtivo(any())).thenReturn(CupomTestObjects.CUPOM_MODEL_LIST);
        given(mapperApp.modelToResponseList(any())).willReturn(CupomTestObjects.CUPOM_RESPONSE_LIST);

        List<CupomResponse> sut = cupomService.findAllCupomByAtivoService(any());

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_RESPONSE_LIST);
    }
    @Test
    void saveCupomServiceTest_SuccessCase(){
        given(mapperApp.requestToModelCreate(any())).willReturn(CupomTestObjects.CUPOM_MODEL);
        when(saveCupom.save(any())).thenReturn(CupomTestObjects.CUPOM_MODEL);
        given(mapperApp.modelToResponse(any())).willReturn(CupomTestObjects.CUPOM_RESPONSE);

        CupomResponse sut = cupomService.saveCupomService(any());

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_RESPONSE);
    }
    @Test
    void updateCupomServiceTest_SuccessCase(){
        given(mapperApp.requestToModel(any())).willReturn(CupomTestObjects.CUPOM_MODEL);
        when(updateCupom.update(any())).thenReturn(CupomTestObjects.CUPOM_MODEL);
        given(mapperApp.modelToResponse(any())).willReturn(CupomTestObjects.CUPOM_RESPONSE);

        CupomResponse sut = cupomService.updateCupomService(any());

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_RESPONSE);
    }
    @Test
    void reactivateCupomServiceTest_SuccessCase(){
        when(reactivateCupom.reactivate(any())).thenReturn(CupomTestObjects.CUPOM_MODEL);
        given(mapperApp.modelToResponse(any())).willReturn(CupomTestObjects.CUPOM_RESPONSE);

        CupomResponse sut = cupomService.reactivateCupomService(any());

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_RESPONSE);
    }
    @Test
    void deactivateCupomServiceTest_SuccessCase(){
        when(deactivateCupom.deactivate(any())).thenReturn(CupomTestObjects.CUPOM_MODEL);
        given(mapperApp.modelToResponse(any())).willReturn(CupomTestObjects.CUPOM_RESPONSE);

        CupomResponse sut = cupomService.deactivateCupomService(any());

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_RESPONSE);
    }

}