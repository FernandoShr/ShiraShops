package com.shirashop.userapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shirashop.userapi.entities.Endereco;
import com.shirashop.userapi.entities.Pais;
import com.shirashop.userapi.entities.UserModel;
import com.shirashop.userapi.exceptions.EntityNotFoundException;
import com.shirashop.userapi.repository.CountryRepository;
import com.shirashop.userapi.repository.EnderecoRepository;
import com.shirashop.userapi.repository.UserRepository;
import com.shirashop.userapi.validations.ValidaEndereco;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EnderecoService {
		
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CountryRepository countryRepository;
	
	// **** MÉTODOS DE CONSULTA ****
	/**
	 * Recupera um Endereço a partir do id. <br>
	 * Caso não encontre lançará EntityNotFoundException
	 * @param endId Id do Endereço
	 * @return Endereço encontrado
	 */
	public Endereco findById(Integer endId) {
		log.info("Pesquisando endereço por id...");
		
		if(endId == null) {
			throw new EntityNotFoundException(
					"Desculpe, não foi possível encontrar um endereço com este id. " + "Verifique e tente novamente.");
		}
		
		Endereco endereco = enderecoRepository.findById(endId).orElseThrow(() -> new EntityNotFoundException(
				"Desculpe, não foi possível encontrar um endereço com este id. " + "Verifique e tente novamente."));
		
		return endereco;
	}
	
	/**
	 * Recupera os Endereços a partir do id de um Usuário.<br>
	 * Caso não encontre o usuário retornará EntityNotFoundException
	 * @param userId Id do Usuário
	 * @return Lista de Endereços encontrados
	 */
	public List<Endereco> findByUserId(Integer userId) {
		log.info("Pesquisando endereços de usuário...");
		
		if(userId == null) {
			throw new EntityNotFoundException(
					"Desculpe, não foi possível encontrar um usuário com este id. " + 
					"Verifique e tente novamente.");
		}
		
		UserModel user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(
				"Desculpe, não foi possível encontrar um usuário com este id. " + 
				"Verifique e tente novamente."));
		
		return user.getEndereco();
	}
	
	// **** MÉTODOS DE CREATE   ****
	public Endereco saveEndereco(Endereco endereco, Integer userId) {
		log.info("Iniciando processo de registro de endereço...");
		
		// Pequisa se o usuário existe no banco de dados
		UserModel user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(
				"Desculpe, não foi possível encontrar um usuário com este id. " + 
				"Verifique e tente novamente."));
		
		
		// Verifica se já há um endereço padrão
		if(user.getEndereco() != null) {
			for(Endereco enderecoPadrao: user.getEndereco()) {
				if(enderecoPadrao.isPadrao()) {
					// *************  LÓGICA DE ENDEREÇO PADRÃO   **********************
					
				}
			}
		}else { //caso seja o primeiro endereço
			
			
		}
		
		// Valida os campos do endereço a ser registrado
		ValidaEndereco.isValidCreate(endereco);
		log.info("Endereço válido");
		

		// *** REGISTROS NO BANCO DE DADOS ***
		Pais paisDb = countryRepository.findByNomeAndCodigo(endereco.getPais().getNome(), endereco.getPais().getCodigo());
		if (paisDb == null) {
			countryRepository.save(endereco.getPais());
		} else {
			endereco.setPais(paisDb);
		}
		log.info("País salvo");
		
		enderecoRepository.save(endereco);
		log.info("Endereço salvo");
		
		// Adiciona o endereço
		user.getEndereco().add(endereco);
		
		// Salva usuário atualizado
		userRepository.save(user);
		log.info("User salvo");
		
		return endereco;
	}
//	
//	// **** MÉTODOS DE UPDATE   ****
//	public Endereco updateEndereco(Endereco endereco) {
//		
//	}
//	
//	// **** MÉTODOS DE DELEÇÃO  ****
//	public Endereco deleteEndereco(Endereco endereco) {
//		
//	}
	
	
	// **** MÉTODOS UTILIZADOS ****
	
//	public boolean existStandardAddress(List<Endereco> userAddress) {
//		
//	}
	
}
