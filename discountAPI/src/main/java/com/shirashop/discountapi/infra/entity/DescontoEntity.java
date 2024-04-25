package com.shirashop.discountapi.infra.entity;

import com.shirashop.discountapi.cross.enums.TipoDesconto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"idObjetoDoDesconto","tipoDesconto"}))
public class DescontoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDesconto;
    private TipoDesconto tipoDesconto;
    private BigDecimal percentualDesconto;
    private Long idObjetoDoDesconto;
    private Boolean isActive;
    private LocalDate dataCriacao;
    private LocalDate dataExpiracao;

}