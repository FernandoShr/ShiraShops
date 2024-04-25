package com.shirashop.discountapi.domain.usecases.cupom;

import com.shirashop.discountapi.domain.gateway.CupomGateway;
import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.domain.usecases.cupom.impl.FindCupomByCodigoImpl;
import com.shirashop.discountapi.testObjects.CupomTestObjects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FindCupomByCodigoImplTest {
    @Mock
    private CupomGateway cupomGateway;
    @InjectMocks
    private FindCupomByCodigoImpl findCupomByCodigo;

    @Test
    void findCupomByCodigoTest_SuccessCase(){
        when(cupomGateway.findCupomByCodigoGateway(anyString())).thenReturn(CupomTestObjects.CUPOM_MODEL);

        CupomModel sut = findCupomByCodigo.findByCodigo(anyString());

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_MODEL);
    }

}