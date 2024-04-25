package com.shirashop.discountapi.domain.usecases.cupom;

import com.shirashop.discountapi.domain.gateway.CupomGateway;
import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.domain.usecases.cupom.impl.SaveCupomImpl;
import com.shirashop.discountapi.testObjects.CupomTestObjects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveCupomImplTest {
    @Mock
    private CupomGateway cupomGateway;
    @InjectMocks
    private SaveCupomImpl saveCupom;

    @Test
    void saveCupomTest_SuccessCase(){
        when(cupomGateway.saveCupomGateway(any())).thenReturn(CupomTestObjects.CUPOM_MODEL);

        CupomModel sut = saveCupom.save(CupomTestObjects.CUPOM_MODEL);

        assertThat(sut).isEqualTo(CupomTestObjects.CUPOM_MODEL);
    }
}
