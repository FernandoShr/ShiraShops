package com.shirashop.pedidoapi.dto.modelMapper;

import com.shirashop.pedidoapi.entities.DetalhePedido;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.shirashop.pedidoapi.dto.DetalhePedidoInput;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DetalhePedidoMapper {
	
	private final ModelMapper mapper;
	
	public DetalhePedidoInput toDetalhePedidoInput(DetalhePedido dp) {
		return mapper.map(dp, DetalhePedidoInput.class);
	}

	public DetalhePedido toDetalhePedido(DetalhePedidoInput dp) {
		return mapper.map(dp, DetalhePedido.class);
	}
	
	
	
}
