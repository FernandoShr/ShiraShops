package com.shirashop.pedidoapi.dto;

import java.math.BigDecimal;
import java.util.List;

import com.shirashop.pedidoapi.constants.StatusPedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoOutput {
	
	private Integer idPedido;	
	private String dataCriacao;
	private String dataModificação;
	private StatusPedido statusPedido;
	private BigDecimal totalPedido;
	private List<DetalhePedidoOutput> detalhePedido;
	private Integer idUsuario;
	
}
