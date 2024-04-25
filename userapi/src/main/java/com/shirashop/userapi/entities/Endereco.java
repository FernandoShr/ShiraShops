package com.shirashop.userapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Endereco {

	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_endereco;
	
	
	@Column(nullable = false)
	private boolean padrao;
	@Column(nullable = false)
	private String apelido;
	
	
	
	@Column(length = 45, nullable = false)
	private String rua;
	@Column(length = 10, nullable = false)
	private String numero;
	@Column(nullable = false)
	private String cep;
	@Column(length = 45, nullable = true)
	private String complemento;
	@Column(length = 45, nullable = false)
	private String bairro;
	@Column(length = 45, nullable = false)
	private String cidade;
	@Column(length = 45, nullable = false)
	private String estado;
	
	// RELACIONAMENTOS
	@ManyToOne
	private Pais pais;
	
}
