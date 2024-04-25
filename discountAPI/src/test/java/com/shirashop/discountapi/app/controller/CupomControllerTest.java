package com.shirashop.discountapi.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shirashop.discountapi.app.dto.cupom.CupomResponse;
import com.shirashop.discountapi.app.mapper.MapperAppCupom;
import com.shirashop.discountapi.app.service.cupom.CupomService;
import com.shirashop.discountapi.testObjects.CupomTestObjects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CupomController.class)
class CupomControllerTest {
    @MockBean
    private CupomService cupomService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MapperAppCupom mapperApp;

    @Test
    void findCupomByIdTest_SuccessCase(){
        when(cupomService.findCupomByIdService(anyLong())).thenReturn(CupomTestObjects.CUPOM_RESPONSE);

        CupomResponse sut = cupomService.findCupomByIdService(anyLong());

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_RESPONSE);
    }
    @Test
    void findCupomByCodigoTest_SuccessCase(){
        when(cupomService.findCupomByCodigoService(anyString())).thenReturn(CupomTestObjects.CUPOM_RESPONSE);

        CupomResponse sut = cupomService.findCupomByCodigoService(anyString());

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_RESPONSE);
    }
    @Test
    void findAllCupomByAtivoTest_SuccessCase(){
        when(cupomService.findAllCupomByAtivoService(anyBoolean())).thenReturn(CupomTestObjects.CUPOM_RESPONSE_LIST);

        List<CupomResponse> sut = cupomService.findAllCupomByAtivoService(anyBoolean());

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_RESPONSE_LIST);
    }
    @Test
    void saveCupomTest_SuccessCase() throws Exception{
        given(mapperApp.requestToModel(CupomTestObjects.CUPOM_REQUEST)).willReturn(CupomTestObjects.CUPOM_MODEL_2);
        given(mapperApp.modelToResponse(CupomTestObjects.CUPOM_MODEL_2)).willReturn(CupomTestObjects.CUPOM_RESPONSE_2);
        when(cupomService.saveCupomService(CupomTestObjects.CUPOM_REQUEST)).thenReturn(CupomTestObjects.CUPOM_RESPONSE_2);

        mockMvc.perform(post("/discount/cupom/save")
                        .content(objectMapper.writeValueAsString(CupomTestObjects.CUPOM_REQUEST))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    @Test
    void updateCupomTest_SuccessCase() throws Exception{
        given(mapperApp.requestToModel(CupomTestObjects.CUPOM_REQUEST)).willReturn(CupomTestObjects.CUPOM_MODEL_2);
        given(mapperApp.modelToResponse(CupomTestObjects.CUPOM_MODEL_2)).willReturn(CupomTestObjects.CUPOM_RESPONSE_2);
        when(cupomService.updateCupomService(CupomTestObjects.CUPOM_REQUEST)).thenReturn(CupomTestObjects.CUPOM_RESPONSE_2);

        mockMvc.perform(put("/discount/cupom/update")
                        .content(objectMapper.writeValueAsString(CupomTestObjects.CUPOM_REQUEST))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void reactivateCupomTest_SuccessCase() throws Exception{
        given(mapperApp.requestToModel(CupomTestObjects.CUPOM_REQUEST)).willReturn(CupomTestObjects.CUPOM_MODEL_2);
        given(mapperApp.modelToResponse(CupomTestObjects.CUPOM_MODEL_2)).willReturn(CupomTestObjects.CUPOM_RESPONSE_2);
        when(cupomService.reactivateCupomService(anyLong())).thenReturn(CupomTestObjects.CUPOM_RESPONSE_2);

        mockMvc.perform(put("/discount/cupom/reactivate/" + anyLong()))
                .andExpect(status().isOk());
    }
    @Test
    void deactivateCupomTest_SuccessCase() throws Exception{
        given(mapperApp.requestToModel(CupomTestObjects.CUPOM_REQUEST)).willReturn(CupomTestObjects.CUPOM_MODEL_2);
        given(mapperApp.modelToResponse(CupomTestObjects.CUPOM_MODEL_2)).willReturn(CupomTestObjects.CUPOM_RESPONSE_2);
        when(cupomService.deactivateCupomService(anyLong())).thenReturn(CupomTestObjects.CUPOM_RESPONSE_2);

        mockMvc.perform(put("/discount/cupom/deactivate/" + anyLong()))
                .andExpect(status().isOk());
    }

}
