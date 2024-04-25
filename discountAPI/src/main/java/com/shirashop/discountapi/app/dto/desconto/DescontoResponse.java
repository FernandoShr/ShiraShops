package com.shirashop.discountapi.app.dto.desconto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shirashop.discountapi.cross.enums.TipoDesconto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DescontoResponse(
        Long idDesconto,
        TipoDesconto tipoDesconto,
        BigDecimal percentualDesconto,
        Long idObjetoDoDesconto,
        Boolean isActive,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataCriacao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataExpiracao
) {
}
