package com.shirashop.userapi.validations;

import java.util.InputMismatchException;

import com.shirashop.userapi.entities.UserModel;
import com.shirashop.userapi.exceptions.InvalidEntityException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidaUser {
	// **** CAMPO NOME ****
	/**
	 * Verifica se o nome possui uma estrutura válida, isto é,
	 * nome com mais de 2 caracteres e menos de 256 caracteres
	 * @param nome Nome do usuário
	 * @return retorna true caso seja válido
	 */
	public static boolean isNomeValid (String nome) {
		if (nome.length() < 2 || 
			nome.length() >= 255) {
				return false;
			}
		else {
			return true;
		}
	}
	
	
	// **** CAMPO SOBRENOME ****
	/**
	 * Verifica se sobrenome tem
	 * no mínimo 2 caracteres e
	 * no máximo 255 
	 * @param sobrenome
	 * @return
	 */
	public static boolean isSobrenomeValid(String sobrenome) {
		if (sobrenome.length() < 2 || 
				sobrenome.length() >= 255) {
					return false;
				}
			else {
				return true;
			}
	}
	
	
	// **** CAMPO CPF ****
	/**
	 * Verifica se o cpf está no formato correto xxx.xxx.xxx-xx
	 * 
	 * @param cpf Recebe cpf como String
	 * @return retorna true se o formato está correto
	 */
	public static boolean isCPFFormated(String cpf) {

		// Verifica se a String está no formato adequado
		if (cpf.matches("\\d{3}.\\d{3}.\\d{3}-\\d{2}")) {
			return true;
		}

		else {
			return false;
		}
	}

	/**
	 * Realiza o calculo de verificação do cpf
	 * 
	 * @param cpf Recebe cpf como String
	 * @return retorna true caso a numeração do cpf esteja correta
	 */
	public static boolean isCPFValid(String cpf) {
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");

		// Validação inicial
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || (cpf.length() != 11)) {

			return (false);
		}

		// Inicio do cálculo
		int primeiroDV = cpf.length() - 2;
		int segundoDV = cpf.length() - 1;
		double calculadoPrimeiroDV;
		double calculadoSegundoDV;
		double soma = 0;

		// try para evitar eventuais problemas de conversão
		try {

			// Calcula o primeiro digito validador
			for (int i = 0, peso = 10; i < primeiroDV; i++) {
				soma += peso * Character.getNumericValue(cpf.charAt(i));
				peso--;
			}

			calculadoPrimeiroDV = 11 - (soma % 11);
			if (calculadoPrimeiroDV == 10 || calculadoPrimeiroDV == 11) {
				calculadoPrimeiroDV = 0;
			}

			/*
			 * Se o priomeiro digito validador estiver errado o CPF já é inválido
			 */
			if (calculadoPrimeiroDV != Character.getNumericValue(cpf.charAt(primeiroDV))) {
				return false;
			}

			// Calculo do segundo digito validador
			soma = 0;
			for (int i = 0, peso = 11; i < segundoDV; i++) {
				soma += peso * Character.getNumericValue(cpf.charAt(i));
				peso--;
			}

			calculadoSegundoDV = 11 - (soma % 11);
			if (calculadoSegundoDV == 10 || calculadoSegundoDV == 11) {
				calculadoSegundoDV = 0;
			}

			// Verificação do segundo digito validador
			if (calculadoSegundoDV != Character.getNumericValue(cpf.charAt(segundoDV))) {
				return false;
			}

			// Passado pelos testes, CPF válido!
			return true;
		}

		catch (InputMismatchException e) {
			e.getMessage();
			return false;
		}
	}	

	
	// **** CAMPO TELEFONE ****
	/**
	 * Verifica se o telefone está em algum dos
	 * formatos válidos:
	 * +xxx(xx)xxxxx-xxxx,
	 * +xxx(xx)xxxx-xxxx,
	 * +xx(xx)xxxxx-xxxx ou
	 * +xx(xx)xxxx-xxxx
	 * @param tel Recebe o telefone como String
	 * @return retorna true caso o formato esteja correto
	 */
	public static boolean isTelValid(String tel) {
		String validaTel = tel.replace(" ", "");
		
		
		// Verifica se a String está no formato adequado
	 	if (validaTel.matches("\\+\\d{3}\\(\\d{2}\\)\\d{5}-\\d{4}") ||
	 		validaTel.matches("\\+\\d{3}\\(\\d{2}\\)\\d{4}-\\d{4}") ||
	 		validaTel.matches("\\+\\d{2}\\(\\d{2}\\)\\d{5}-\\d{4}") ||
	 		validaTel.matches("\\+\\d{2}\\(\\d{2}\\)\\d{4}-\\d{4}")) {
	 		return true;
	 	}
	 	
	 	else {
	 		return false;
	 	}
	}
	
	/**
	 * Verifica se o Email possui menos de 255 caracteres
	 * @param email
	 * @return <b>true</b> caso atenda a condição, <b>false</b> caso não
	 */
	public static boolean isEmailValid(String email) {
		
		if (email.length() > 255) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Verifica se a Senha possui entre 6 a 255 caracteres
	 * @param password
	 * @return <b>true</b> caso atenda a condição, <b>false</b> caso não
	 */
	public static boolean isPasswordValid(String password) {
		
		if (password.length() > 255 || password.length() < 6) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Verifica se o usuário está válido
	 * para ser registrado no banco de dados
	 * @param user Recebe um objeto usuário
	 */
	public static void isValidCreate(UserModel user) {
		log.info("Validando campos de usuário...");
		
		// Verifica campos nulos
		ValidaCampoNulo.isNull(user);
		
		// ******* VERIFICA O NOME ******
		// Se o nome não for válido
		if (!(isNomeValid(user.getNome()))) {
			throw new InvalidEntityException("Nome inválido");
		}
		
		
		// ******* VERIFICA O SOBRENOME ******
		// Se sobrenome não for válido
		if (!(isSobrenomeValid(user.getSobrenome()))) {
			throw new InvalidEntityException("Sobrenome inválido");
		}
		
		
		// ****** VERIFICA CPF ********
		// Se o CPF não estiver no formato correto
		if (!(isCPFFormated(user.getCpf()))) {
			throw new InvalidEntityException(
					"CPF inválido, por gentileza digitar no formato: "
					+ "xxx.xxx.xxx-xx");
		}
		// Se o CPF não tiver uma numeração válida
		if (!(isCPFValid(user.getCpf()))) {
			throw new InvalidEntityException("CPF inválido");
		}
		
		
		// ****** VERIFICA O TELEFONE *******
		// Se o telefone não estiver no formato correto
		if (user.getTelefone() != null && 
			!(isTelValid(user.getTelefone()))) {
			throw new InvalidEntityException(
					"Formato do telefone inválido. "
					+ "Por favor, informe o telefone em algum desses formatos:\n"
					+ "+xxx (xx) xxxxx-xxxx ou\n"
					+ "+xxx (xx) xxxx-xxxx ou\n"
					+ "+xx (xx) xxxxx-xxxx ou\n"
					+ "+xx (xx) xxxx-xxxx");
		}
		///////////////
		
		// **** VERIFICA ENDERECO *****
		ValidaEndereco.isValidCreate(user.getEndereco());
	
	}
	
	/**
	 * Verifica se os dados vindos do objeto estão válido para
	 * atualização
	 * @param userdb Usuário já registrado no banco de dados
	 * @param user Informações a serem atualizadas
	 * @return Retorna o usuário com campos válidos
	 */
	public static UserModel isValidUpdate(UserModel userdb, UserModel user) {
		log.info("Validando campos de usuário a serem atualizados...");
		
		// ******* VERIFICA O NOME ******
		// Mantém o nome caso o usuário não queira atualizar este campo
		if (user.getNome() == null){
			user.setNome(userdb.getNome());
		}
		// Se o nome não for válido
		else if (!(ValidaUser.isNomeValid(user.getNome()))) {
			throw new InvalidEntityException("Nome inválido");
		}

		
		// ******* VERIFICA O SOBRENOME ******
		// Mantém o sobrenome caso o usuário não queira atualizar este campo
		if (user.getSobrenome() == null) {
			user.setSobrenome(userdb.getSobrenome());
		}
		// Se sobrenome não for válido
		else if (!(ValidaUser.isSobrenomeValid(user.getSobrenome()))) {
			throw new InvalidEntityException("Sobrenome inválido");
		}
		
		
		// ****** VERIFICA CPF ********
		// Usuário não pode alterar CPF
		if (user.getCpf() != null) {
			throw new InvalidEntityException(
					"Não foi possível atualizar as suas informações. "
					+ "Não é permitido alterar o CPF.");
		}
		user.setCpf(userdb.getCpf());
		
		
		// ****** VERIFICA O TELEFONE *******
		if (user.getTelefone() == null) {
			user.setTelefone(userdb.getTelefone());
		}
		// Se o telefone não estiver no formato correto
		else if (!(ValidaUser.isTelValid(user.getTelefone()))) {
			throw new InvalidEntityException(
					"Formato do telefone inválido. "
					+ "Por favor, informe o telefone em algum desses formatos:\n"
					+ "+xxx (xx) xxxxx-xxxx ou\n"
					+ "+xxx (xx) xxxx-xxxx ou\n"
					+ "+xx (xx) xxxxx-xxxx ou\n"
					+ "+xx (xx) xxxx-xxxx");
		}
		
		
		return user;
	}
}
