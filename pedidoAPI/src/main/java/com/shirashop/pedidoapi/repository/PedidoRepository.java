package com.shirashop.pedidoapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shirashop.pedidoapi.constants.StatusPedido;
import com.shirashop.pedidoapi.entities.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Integer> {
	
	public List<Pedido> findByIdUsuario(Integer idUsuario); 
	
	public List<Pedido> findByStatusPedido(StatusPedido statusPedido);
}
