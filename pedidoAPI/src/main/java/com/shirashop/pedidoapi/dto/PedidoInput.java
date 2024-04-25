package com.shirashop.pedidoapi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoInput {
	
	private Integer idUsuario;
	private List<DetalhePedidoInput> detalhePedido;
	
}
