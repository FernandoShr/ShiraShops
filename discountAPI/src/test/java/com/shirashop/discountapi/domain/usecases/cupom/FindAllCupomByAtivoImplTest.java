package com.shirashop.discountapi.domain.usecases.cupom;

import com.shirashop.discountapi.domain.gateway.CupomGateway;
import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.domain.usecases.cupom.impl.FindAllCupomByAtivoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.shirashop.discountapi.testObjects.CupomTestObjects.CUPOM_MODEL_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllCupomByAtivoImplTest {
    @Mock
    private CupomGateway cupomGateway;
    @InjectMocks
    private FindAllCupomByAtivoImpl findAllCupomByAtivo;

    @Test
    void findAllCupomByAtivoTest_SuccessCase(){
        when(cupomGateway.findAllCupomByAtivoGateway(anyBoolean())).thenReturn(CUPOM_MODEL_LIST);

        List<CupomModel> sut = findAllCupomByAtivo.findAllByAtivo(anyBoolean());

        assertThat(sut).isEqualTo(CUPOM_MODEL_LIST);
    }
}
