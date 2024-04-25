package com.shirashop.pedidoapi.dto.modelMapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.shirashop.pedidoapi.dto.PedidoInput;
import com.shirashop.pedidoapi.dto.PedidoOutput;
import com.shirashop.pedidoapi.entities.Pedido;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PedidoMapper {
	
	private final ModelMapper mapper;
	
	public Pedido toPedido(PedidoInput pedidoInput) {
		return mapper.map(pedidoInput,Pedido.class);
	}

	public Pedido toPedido(PedidoOutput pedidoOut) {
		return mapper.map(pedidoOut,Pedido.class);
	}
	
	public PedidoInput toPedidoInput(Pedido pedido) {
		return mapper.map(pedido, PedidoInput.class);
	}

	public PedidoOutput toPedidoOutput(Pedido pedido) {
		return mapper.map(pedido, PedidoOutput.class);
	}
	
	public List<PedidoOutput> toListPedidoOutput(List<Pedido> pedidos){
		List<PedidoOutput> pdoList = new ArrayList<>();
		for(Pedido pd: pedidos) {
			pdoList.add(toPedidoOutput(pd));
		}
		
		return pdoList;
	}
	
}
