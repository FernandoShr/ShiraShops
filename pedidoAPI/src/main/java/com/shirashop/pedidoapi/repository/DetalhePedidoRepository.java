package com.shirashop.pedidoapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shirashop.pedidoapi.entities.DetalhePedido;

@Repository
public interface DetalhePedidoRepository extends CrudRepository<DetalhePedido, Integer>{

}
