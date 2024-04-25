package com.shirashop.discountapi.domain.usecases.cupom;

import com.shirashop.discountapi.domain.gateway.CupomGateway;
import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.domain.usecases.cupom.impl.DeactivateCupomImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.shirashop.discountapi.testObjects.CupomTestObjects.CUPOM_MODEL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeactivateCupomImplTest {
    @Mock
    private CupomGateway cupomGateway;
    @InjectMocks
    private DeactivateCupomImpl deactivateCupom;

    @Test
    void deactivateCupomTest_SuccessCase(){
        when(cupomGateway.deactivateCupomGateway(anyLong())).thenReturn(CUPOM_MODEL);

        CupomModel sut = deactivateCupom.deactivate(1L);

        assertThat(sut).isEqualTo(CUPOM_MODEL);
    }
}
