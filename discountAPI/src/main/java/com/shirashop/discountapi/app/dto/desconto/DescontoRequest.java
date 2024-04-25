package com.shirashop.discountapi.app.dto.desconto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shirashop.discountapi.cross.enums.TipoDesconto;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DescontoRequest(
        @NotNull(message = "Tipo do desconto é obrigatório")
        TipoDesconto tipoDesconto,
        @Min(value = 0, message = "Valor mínimo é zero")
        @Max(value = 1, message = "Valor máximo é 1")
        @NotNull(message = "O percentual do desconto é obrigatório")
        BigDecimal percentualDesconto,
        @Positive(message = "Id do Objeto precisa ser um número maior que zero")
        @NotNull(message = "Id do objeto de desconto é obrigatório")
        Long idObjetoDoDesconto,
        @NotNull(message = "A data de expiração é obrigatória")
        @Future(message = "A data precisa ser superior a data atual")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataExpiracao
) {
}