package com.shirashop.userapi.validations;

import java.util.List;

import com.shirashop.userapi.entities.Endereco;
import com.shirashop.userapi.exceptions.InvalidEntityException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidaEndereco {

	
	// **** CAMPO CEP ****
	/**
	 * Verifica se o cep esta no formato válido: xxxxx-xxx
	 * 
	 * @param cep String cep
	 * @return retorna true caso seja válido
	 */
	public static boolean isCepValid(String cep) {

		// Verifica se a String está no formato adequado
		if (cep.matches("\\d{5}-\\d{3}")) {
			return true;
		}

		return false;
	}

	/**
	 * Verifica se endereco tem os campos válidos para registrar no banco de dados
	 * 
	 * @param endereco Objeto Endereco
	 * @return retorna true caso esteja válido
	 */
	public static boolean isValidCreate(List<Endereco> enderecos) {
		log.info("Validando Criação de Endereço...");

		if (enderecos != null) {
			int lim45 = 45;
			int lim10 = 10;

			for (Endereco endereco : enderecos) {

				ValidaCampoNulo.isNull(endereco);

				// ***** VERIFICA RUA ******
				if (endereco.getRua().length() > lim45) {
					throw new InvalidEntityException("O campo rua deve ter no máximo " + "45 caracteres.");
				}

				// ***** VERIFICA NÚMERO ******
				if (endereco.getNumero().length() > lim10) {
					throw new InvalidEntityException("O campo número deve ter no máximo " + "10 caracteres.");
				}

				// ***** VERIFICA CEP ******
				// Se o CEP não estiver no formato correto
				if (!(isCepValid(endereco.getCep()))) {
					throw new InvalidEntityException(
							"O CEP não está em um formato válido. " + "Verifique e tente novamente.");
				}

				// ***** VERIFICA COMPLEMENTO *****
				if (endereco.getComplemento() != null && endereco.getComplemento().length() > lim45) {
					throw new InvalidEntityException("O campo complemento deve ter no máximo " + "45 caracteres.");
				}

				// ***** VERIFICA BAIRRO *****
				if (endereco.getBairro().length() > lim45) {
					throw new InvalidEntityException("O campo bairro deve ter no máximo " + "45 caracteres.");
				}

				// ***** VERIFICA CIDADE *****
				if (endereco.getCidade().length() > lim45) {
					throw new InvalidEntityException("O campo cidade deve ter no máximo " + "45 caracteres.");
				}

				// ***** VERIFICA ESTADO *****
				if (endereco.getEstado().length() > lim45) {
					throw new InvalidEntityException("O campo estado deve ter no máximo " + "45 caracteres.");
				}

				// ***** VERIFICA APELIDO *****
				if (endereco.getApelido().length() > lim45) {
					throw new InvalidEntityException("O campo apelido deve ter no máximo 45 caracteres");
				}

				// **** VERIFICA PAÍS ****
				ValidaCampoNulo.isNull(endereco.getPais());

				// Se Código do país não for válido
				if (!ValidaPais.isCodigoValid(endereco.getPais().getCodigo())) {
					throw new InvalidEntityException("Campo código de país inválido."
							+ " Por favor digite em algum dos formatos:\n" + "+xx ou +xxx");
				}
			}
		}
		return true;
	}

	/**
	 * Verifica se endereco tem os campos válidos para registrar no banco de dados
	 * 
	 * @param endereco Objeto Endereco
	 * @return retorna true caso esteja válido
	 */
	public static boolean isValidCreate(Endereco endereco) {
		log.info("Validando Criação de Endereço...");

		if (endereco != null) {
			int lim45 = 45;
			int lim10 = 10;

			ValidaCampoNulo.isNull(endereco);

			// ***** VERIFICA RUA ******
			if (endereco.getRua().length() > lim45) {
				throw new InvalidEntityException("O campo rua deve ter no máximo " + "45 caracteres.");
			}

			// ***** VERIFICA NÚMERO ******
			if (endereco.getNumero().length() > lim10) {
				throw new InvalidEntityException("O campo número deve ter no máximo " + "10 caracteres.");
			}

			// ***** VERIFICA CEP ******
			// Se o CEP não estiver no formato correto
			if (!(isCepValid(endereco.getCep()))) {
				throw new InvalidEntityException(
						"O CEP não está em um formato válido. " + "Verifique e tente novamente.");
			}

			// ***** VERIFICA COMPLEMENTO *****
			if (endereco.getComplemento() != null && endereco.getComplemento().length() > lim45) {
				throw new InvalidEntityException("O campo complemento deve ter no máximo " + "45 caracteres.");
			}

			// ***** VERIFICA BAIRRO *****
			if (endereco.getBairro().length() > lim45) {
				throw new InvalidEntityException("O campo bairro deve ter no máximo " + "45 caracteres.");
			}

			// ***** VERIFICA CIDADE *****
			if (endereco.getCidade().length() > lim45) {
				throw new InvalidEntityException("O campo cidade deve ter no máximo " + "45 caracteres.");
			}

			// ***** VERIFICA ESTADO *****
			if (endereco.getEstado().length() > lim45) {
				throw new InvalidEntityException("O campo estado deve ter no máximo " + "45 caracteres.");
			}

			// ***** VERIFICA APELIDO *****
			if (endereco.getApelido().length() > lim45) {
				throw new InvalidEntityException("O campo apelido deve ter no máximo 45 caracteres");
			}

			// **** VERIFICA PAÍS ****
			ValidaCampoNulo.isNull(endereco.getPais());

			// Se Código do país não for válido
			if (!ValidaPais.isCodigoValid(endereco.getPais().getCodigo())) {
				throw new InvalidEntityException("Campo código de país inválido."
						+ " Por favor digite em algum dos formatos:\n" + "+xx ou +xxx");
			}
		}

		return true;
	}
}
