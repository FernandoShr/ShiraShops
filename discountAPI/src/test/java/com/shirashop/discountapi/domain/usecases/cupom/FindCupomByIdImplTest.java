package com.shirashop.discountapi.domain.usecases.cupom;

import com.shirashop.discountapi.domain.gateway.CupomGateway;
import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.domain.usecases.cupom.impl.FindCupomByIdImpl;
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
class FindCupomByIdImplTest {
    @Mock
    private CupomGateway cupomGateway;
    @InjectMocks
    private FindCupomByIdImpl findCupomById;

    @Test
    void findCupomByIdTest_SuccessCase(){
        when(cupomGateway.findCupomByIdGateway(anyLong())).thenReturn(CUPOM_MODEL);

        CupomModel sut = findCupomById.findById(anyLong());

        assertThat(sut).isEqualTo(CUPOM_MODEL);
    }
}
