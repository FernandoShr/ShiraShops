package com.shirashop.discountapi.infra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CupomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String codigo;
    private BigDecimal percentual;
    private Integer qtdMinimaProdutos;
    private BigDecimal valorMinimoPedido;
    private BigDecimal descontoMaximo;
    private LocalDate dataCriacao;
    private LocalDate dataExpiracao;
    private Boolean ativo;
}