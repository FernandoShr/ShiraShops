package com.shirashop.productapi.validations;

import com.shirashop.productapi.entities.Categoria;
import com.shirashop.productapi.exceptions.InvalidEntityException;

public class ValidaCategoria {
	
	/**
	 * Verifica se o nome possui entre 3 a 45 caracteres
	 * @param nomeCategoria Nome da Categoria
	 * @return retorna true caso seja válido
	 */
	public static boolean isNomeValid(String nomeCategoria) {
		
		if(nomeCategoria.length() < 3 || 
		   nomeCategoria.length() > 45) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Verifica se os campos de categoria estão preenchido corretamente
	 * para o registro da categoria no banco de dados
	 * @param categoria Recebe objeto Categoria
	 */
	public static void isValidCreate(Categoria categoria) {
		System.out.println("[ValidaCategoria] : in isValidCreate");
		
		// Verifica campos obrigatórios
		ValidaCampoNulo.isNull(categoria);
		
		// **** VERIFICA NOME ****
		// Se o nome não for válido
		if(!isNomeValid(categoria.getNomeCategoria())) {
			throw new InvalidEntityException(
					"Ops! O nome da categoria deve ter entre 3 e 45 caracteres. "
					+ "Verifique e tente novamente.");
		}
		
		System.out.println("[ValidaCategoria] : end isValidCreate");
	}
	
	/**
	 * Verifica se os campos estão preenchidos corretamente para
	 * atualizar a categoria no banco de dados
	 * @param categoria Objeto Categoria
	 */
	public static void isValidUpdate(Categoria categoria) {
		System.out.println("[ValidaCategoria] : in isValidUpdate");
		
		// **** VERIFICA NOME ****
		// Se o nome não for válido
		if(categoria.getNomeCategoria() == null || !isNomeValid(categoria.getNomeCategoria())) {
			throw new InvalidEntityException(
					"Ops! O nome da categoria deve ter entre 3 e 45 caracteres. "
					+ "Verifique e tente novamente.");
		}
		
		System.out.println("[ValidaCategoria] : end isValidUpdate");
	}
}
