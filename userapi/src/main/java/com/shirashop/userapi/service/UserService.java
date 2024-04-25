package com.shirashop.userapi.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.shirashop.userapi.entities.Endereco;
import com.shirashop.userapi.entities.Pais;
import com.shirashop.userapi.entities.UserModel;
import com.shirashop.userapi.repository.CountryRepository;
import com.shirashop.userapi.repository.EnderecoRepository;
import com.shirashop.userapi.repository.UserRepository;
import com.shirashop.userapi.validations.ValidaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shirashop.userapi.exceptions.EntityNotFoundException;
import com.shirashop.userapi.exceptions.InvalidEntityException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CountryRepository countryRepository;

	// **** MÉTODOS DE CONSULTA ****

	// CONSULTA POR ID
	/**
	 * Busca por um usuário com o respectivo id no banco de dados
	 * 
	 * @param id_usuario id do usuário
	 * @return retorna o objeto usuário encontrado, caso não encontre lançará
	 *         exceção EntityNotFoundException
	 */
	public UserModel findById(Integer id_usuario) {
		log.info("Pesquisando usuário por id...");
		
		if (id_usuario == null) {
			throw new EntityNotFoundException(
					"Desculpe, não foi possível encontrar um usuário com este id. " + "Verifique e tente novamente.");
		}

		UserModel user = userRepository.findById(id_usuario).orElseThrow(() -> new EntityNotFoundException(
				"Desculpe, não foi possível encontrar um usuário com este id. " + "Verifique e tente novamente."));
		return user;
	}

	/**
	 * Busca por um usuário ativo com o respectio id no banco de dados
	 * 
	 * @param id_usuario id do usuário
	 * @return retorna objeto usuário
	 */
	public UserModel findByIdAndActive(Integer id_usuario) {
		log.info("Pesquisando usuário...");
		
		UserModel user = findById(id_usuario);
		// Se o usuario não estiver ativo
		if (!user.isAtivo()) {
			throw new EntityNotFoundException(
					"Desculpe, não foi possível encontrar um usuário com este id. " + "Verifique e tente novamente.");
		}

		return user;
	}

	// CONSULTAR TODOS USUARIOS
	/**
	 * Busca por todos os usuário do banco de dados
	 * 
	 * @return retorna uma lista de objetos encontrados
	 */
	public List<UserModel> findAll() {
		return (List<UserModel>) userRepository.findAll();
	}

	// CONSULTA POR NOME
	/**
	 * Busca pelo nome do usuário ativo no banco de dados
	 * 
	 * @param nome Nome do usuário
	 * @return retorna uma lista de objetos usuário encontrados
	 */
	public List<UserModel> findByNome(String nome) {
		log.info("Pesquisando usuário por nome...");
		
		// Se o nome não for válido
		if (!ValidaUser.isNomeValid(nome)) {
			throw new InvalidEntityException("Desculpe, não foi possível realizar a busca por nome."
					+ "O nome informado deve ter, pelo menos, 2 caracteres");
		}

		List<UserModel> users = userRepository.findByNomeAndAtivo(nome, true);
		// Se não for encontrado nenhum usuário com este nome
		if (users.size() == 0) {
			throw new EntityNotFoundException(
					"Não foi possível encontrar um usuário com este nome. " + "Verifique e tente novamente.");
		}

		return users;
	}

	// CONSULTA POR CPF
	/**
	 * Busca um usuário ativo pelo cpf no banco de dados
	 * 
	 * @param cpf CPF do usuário
	 * @return retorna o objeto encontrado
	 */
	public UserModel findByCpf(String cpf) {
		log.info("Pesquisando usuário por CPF...");
		
		// Se o cpf não estiver no formato correto
		if (!(ValidaUser.isCPFFormated(cpf))) {
			throw new InvalidEntityException("Desculpe, não foi possível realizar a busca por CPF. "
					+ "O CPF não foi digitado corretamente. " + "Verifique e tente novamente.");
		}

		UserModel user = userRepository.findByCpfAndAtivo(cpf, true);
		// Se não for encontrado um usuário com este cpf
		if (user == null) {
			throw new EntityNotFoundException(
					"Desculpe, não foi possível encontrar um usuário com este cpf. " + "Verifique e tente novamente.");
		}

		return user;
	}

	// BUSCA PAÍS (COUNTRY)
	/**
	 * Consulta banco de dados para conferir se já existe país cadastrado
	 * 
	 * @param pais
	 * @return retorna true caso encontre
	 */
	public Pais findPais(Pais pais) {
		log.info("Pesquisando país...");
		
		Pais paisPesquisado = countryRepository.findByNomeAndCodigo(pais.getNome(), pais.getCodigo());

		// Se não encontrar um pais já cadastrado
		if (paisPesquisado == null) {
			return null;
		}

		return paisPesquisado;
	}

	// **** MÉTODOS DE CREATE ****

	/**
	 * Valida e salva o usuário no banco de dados
	 * 
	 * @param user Objeto usuário do tipo UserModel
	 */
	@Transactional
	public void saveUser(UserModel user) {
		log.info("Iniciando processo de criação de usuário...");
		
		// Verifica se já não existe um usuário cadastrado com este cpf
		if (userRepository.findByCpfAndAtivo(user.getCpf(), true) != null) {
			throw new InvalidEntityException("CPF já cadastrado. " + "Por favor tente novamente");
		}

		// Valida os dados do usuário
		ValidaUser.isValidCreate(user);
		log.info("Usuário válido!");
		
		// Usuário ativo
		user.setAtivo(true);

		// ***** SALVA A DATA ATUAL ********
		// Data de criação do Usuário
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		user.setData_criacao(dtf.format(LocalDateTime.now()));
		
		if(user.getEndereco() != null) {
			// ***** SALVA PAÍS ******
			// Se não encontrar um país já cadastrado, irá registrá-lo
			for (Endereco endereco : user.getEndereco()) {
				
				Pais paisDb = findPais(endereco.getPais());
				if (paisDb == null) {
					this.countryRepository.save(endereco.getPais());
				} else {
					endereco.setPais(paisDb);
				}
				
			}
			// ***** SALVA ENDERECO ******
			this.enderecoRepository.saveAll(user.getEndereco());			
		}
		
		// ***** SALVA USUÁRIO *********
		// Usuário salvo no banco de dados
		userRepository.save(user);
	}

	// **** MÉTODOS UPDATE ****

	/**
	 * Valida e atualiza as informações do usuário
	 * 
	 * @param user Objeto usuário do tipo UserModel
	 */
	public void updateUser(UserModel user) {
		log.info("Iniciando processo de atualização de usuário...");
		// Usuário no banco de dados
		UserModel userdb = findByIdAndActive(user.getIdUsuario());

		// Valida os dados de entrada para continuar a atualização
		user = ValidaUser.isValidUpdate(userdb, user);
		log.info("Usuário válido");
		
		// **** SALVA A DATA DE ATUALIZAÇÃO *****
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		user.setData_modificacao(dtf.format(LocalDateTime.now()));

		// **** RECUPERA A DATA DE CRIAÇÃO *****
		user.setData_criacao(userdb.getData_criacao());

		// **** RECUPERA DADO ATIVO ****
		user.setAtivo(userdb.isAtivo());

		// ***** SALVA PAÍS ******
		// Mantém caso o usuário não queira atualizar este campo
//		if (user.getEndereco() != null) {
//			if (user.getEndereco().getPais() == null) {
//				user.getEndereco().setPais(userdb.getEndereco().getPais());
//			}
//
//			this.countryRepository.save(user.getEndereco().getPais());
//		}
//
//		if (user.getEndereco() == null) {
//			user.setEndereco(userdb.getEndereco());
//			this.enderecoRepository.save(user.getEndereco());
//		}

//		// ***** SALVA ENDERECO ******
		// Modificações de endereço serão feitas na controller de endereço
		user.setEndereco(userdb.getEndereco());
		
		// ***** SALVA MODIFICAÇÕES *********
		// Usuário salvo no banco de dados
		userRepository.save(user);
	}

	/**
	 * Reativa o Usuário inativo
	 * 
	 * @param user Objeto user do tipo UserModel
	 */
	public void reactiveUser(UserModel user) {
		log.info("Iniciando processo de reativação de usuário...");
		
		UserModel reactiveUser = findById(user.getIdUsuario());

		if (reactiveUser.isAtivo()) {
			throw new InvalidEntityException("Este usuário já está ativo. Por favor, tente novamente");
		}

		if (userRepository.findByCpfAndAtivo(reactiveUser.getCpf(), true) != null) {
			throw new InvalidEntityException(
					"Não foi possível reativar a conta. " + "CPF já cadastrado em outra conta.");
		}
		reactiveUser.setAtivo(true);
		userRepository.save(reactiveUser);
		log.info("Usuário reativado!");
	}

	// **** MÉTODOS DELETE ****
	/**
	 * Inativa o usuário no banco de dados
	 * 
	 * @param user Objeto usuário do tipo UserModel
	 */
	public void deleteUser(UserModel user) {
		log.info("Iniciando processo de exclusão de usuário...");
		// Se encontrar o usuário, torna-o inativo
		UserModel delUser = findByIdAndActive(user.getIdUsuario());
		delUser.setAtivo(false);
		userRepository.save(delUser);
		// userRepository.delete(user);
	}

}
