package com.shirashop.userapi.entities.fromPedido;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    private Integer idPedido;

    // Calculado automaticamente
    private String dataCriacao;

    private String dataModificacao;

    private StatusPedido statusPedido;

    // Calculado automaticamente
    private BigDecimal totalPedido;

    private Integer idUsuario;

    private List<DetalhePedido> detalhePedido;

}
