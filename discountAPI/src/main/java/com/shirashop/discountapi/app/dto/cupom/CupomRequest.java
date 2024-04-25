package com.shirashop.discountapi.app.dto.cupom;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
public record CupomRequest(
         @NotBlank(message = "O código do cupom é obrigatório")
         String codigo,
         @Min(value = 0, message = "Valor mínimo é zero")
         @Max(value = 1, message = "Valor máximo é 1")
         @NotNull(message = "O percentual de desconto é obrigatório")
         BigDecimal percentual,
         @Min(value = 1, message = "A quantidade mínima precisa ser maior do que zero")
         Integer qtdMinimaProdutos,
         @PositiveOrZero(message = "O valor precisa ser positivo")
         BigDecimal valorMinimoPedido,
         @PositiveOrZero(message = "O valor precisa ser positivo")
         BigDecimal descontoMaximo,
         @NotNull(message = "A data de expiração é obrigatória")
         @Future(message = "A data precisa ser superior a data atual")
         @JsonFormat(pattern = "dd/MM/yyyy")
         LocalDate dataExpiracao
) {
}
