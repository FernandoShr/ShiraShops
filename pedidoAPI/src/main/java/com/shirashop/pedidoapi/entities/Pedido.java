package com.shirashop.pedidoapi.entities;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.shirashop.pedidoapi.constants.StatusPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonPropertyOrder({"idPedido", "idUsuario"})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id_pedido")
	private Integer idPedido;
	
	// Calculado automaticamente
	@Column
	private String dataCriacao;
	
	@Column
	private String dataModificacao;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusPedido statusPedido;
	
	// Calculado automaticamente
	@Column
	private BigDecimal totalPedido;
	
	@Column(nullable = false)
	private Integer idUsuario;
	
	@OneToMany
	@JoinColumn(name="id_pedido")
	private List<DetalhePedido> detalhePedido;
	
}
