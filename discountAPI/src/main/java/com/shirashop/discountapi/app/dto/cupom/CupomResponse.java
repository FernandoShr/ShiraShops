package com.shirashop.discountapi.app.dto.cupom;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CupomResponse(
        Long id,
        String codigo,
        BigDecimal percentual,
        Integer qtdMinimaProdutos,
        BigDecimal valorMinimoPedido,
        BigDecimal descontoMaximo,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataCriacao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataExpiracao,
        Boolean ativo
) {
}
