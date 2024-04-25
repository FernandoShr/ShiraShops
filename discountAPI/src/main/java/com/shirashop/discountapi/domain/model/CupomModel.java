package com.shirashop.discountapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CupomModel {

    private Long id;
    private String codigo;
    private BigDecimal percentual;
    private Integer qtdMinimaProdutos;
    private BigDecimal valorMinimoPedido;
    private BigDecimal descontoMaximo;
    private LocalDate dataCriacao;
    private LocalDate dataExpiracao;
    private Boolean ativo;

}
