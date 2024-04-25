package com.shirashop.userapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shirashop.userapi.entities.Pais;

@Repository
public interface CountryRepository extends CrudRepository<Pais, Integer>{
	
	public Pais findByNomeAndCodigo (String nome, String codigo);
}
