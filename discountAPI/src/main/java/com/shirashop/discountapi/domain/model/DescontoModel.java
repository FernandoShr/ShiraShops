package com.shirashop.discountapi.domain.model;

import com.shirashop.discountapi.cross.enums.TipoDesconto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Data
public class DescontoModel {

    private Long idDesconto;
    private TipoDesconto tipoDesconto;
    private BigDecimal percentualDesconto;
    private Long idObjetoDoDesconto;
    private Boolean isActive;
    private LocalDate dataCriacao;
    private LocalDate dataExpiracao;

}

