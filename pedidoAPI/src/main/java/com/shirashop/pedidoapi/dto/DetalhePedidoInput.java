package com.shirashop.pedidoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalhePedidoInput {
	
	private Integer quantidade;
	private Integer idProduto;
	
}
