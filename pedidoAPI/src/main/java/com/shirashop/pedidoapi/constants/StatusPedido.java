package com.shirashop.pedidoapi.constants;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum StatusPedido {
	CANCELADO("0"),
	AGPAGAMENTO("1"),
	EFETUADO("2"),
	PROCESSADO("3"),
	CONCLUIDO("4");

	private String valor;
	
	StatusPedido(String valor) {
		this.valor = valor;
	}
	
	public static StatusPedido getByValor(String valor) {
		for(StatusPedido status: StatusPedido.values()) {
			if(status.valor.equals(valor)) {
				return status;
			}
		}
		log.warn("Busca de status inválida");
		return null;
	}
}
