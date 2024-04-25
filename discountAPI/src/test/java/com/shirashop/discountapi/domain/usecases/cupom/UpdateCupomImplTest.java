package com.shirashop.discountapi.domain.usecases.cupom;

import com.shirashop.discountapi.domain.gateway.CupomGateway;
import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.domain.usecases.cupom.impl.UpdateCupomImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.shirashop.discountapi.testObjects.CupomTestObjects.CUPOM_MODEL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCupomImplTest {
    @Mock
    private CupomGateway cupomGateway;
    @InjectMocks
    private UpdateCupomImpl updateCupom;

    @Test
    void updateCupomTest_SuccessCase(){
        when(cupomGateway.updateCupomGateway(any())).thenReturn(CUPOM_MODEL);

        CupomModel sut = updateCupom.update(any());

        assertThat(sut).isEqualTo(CUPOM_MODEL);
    }
}
