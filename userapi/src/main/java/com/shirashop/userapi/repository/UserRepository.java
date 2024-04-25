package com.shirashop.userapi.repository;

import java.util.List;

import com.shirashop.userapi.entities.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibmshop.userapi.entities.*;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer>{
	
	public List<UserModel> findByNome(String nome);	
	
	public List<UserModel> findByNomeAndAtivo(String nome, boolean ativo);
	
	public UserModel findByCpf(String cpf);

	public UserModel findByCpfAndAtivo(String cpf, boolean ativo);
	
	public UserModel findByAtivo(boolean ativo);
}
