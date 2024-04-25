package com.shirashop.pedidoapi.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalhePedidoOutput {
	
	private Integer idDetalhePedido;
	private Integer quantidade;
	private BigDecimal subTotal;
	private ProductDTO produto;
	
}
