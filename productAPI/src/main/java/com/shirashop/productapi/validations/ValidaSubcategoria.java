package com.shirashop.productapi.validations;

import com.shirashop.productapi.entities.Subcategoria;
import com.shirashop.productapi.exceptions.InvalidEntityException;

public class ValidaSubcategoria {
	
	/**
	 * Verifica se o nome da Subcategoria é válido,
	 * isto é, possui entre 3 a 45 caracteres
	 * @param nome 
	 * @return true caso esteja válido
	 */
	public static boolean isNomeValid(String nome) {
		
		if(nome.length() < 3 || nome.length() > 45) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Verifica se a descrição da Subcategoria é válida,
	 * isto é, possui entre 15 a 255 caracteres
	 * @param descricao
	 * @return true caso esteja válido
	 */
	public static boolean isDescValid(String descricao) {
		
		if(descricao.length() < 15 || descricao.length() > 255) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Valida os campos da Subcategoria a ser registrada
	 * @param subcategoria
	 */
	public static void isValidCreate(Subcategoria subcategoria) {
		System.out.println("[ValidaSubcategoria] : in isValidCreate");
		
		// Verifica se todos os campos obrigatórios estão preenchidos
		ValidaCampoNulo.isNull(subcategoria);
		
		// Se o nome não for válido
		if(!isNomeValid(subcategoria.getNomeSubcategoria())) {
			throw new InvalidEntityException(
					"O nome não pode ter menos de 3 e mais de 45 "
					+ "caracteres");
		}
		// Se a descrição não for válida
		if(!isDescValid(subcategoria.getDescricao())) {
			throw new InvalidEntityException(
					"A descrição não pode ter menos de 15 e mais de 255 "
					+ "caracteres");
		}
		
		System.out.println("[ValidaSubcategoria] : end isValidCreate");
	}
	
	/**
	 * Valida os campos da Subcategoria a ser atualizada
	 * @param subcategoriaDb Subcategoria já registrada no banco de dados
	 * @param subcategoria Subcategoria com as informações atualizadas
	 * @return retorna a Subcategoria atualizada
	 */
	public static Subcategoria isValidUpdate(Subcategoria subcategoriaDb,
											 Subcategoria subcategoria) {
		System.out.println("[ValidaSubcategoria] : in isValidUpdate");
		
		// Não é necessário reescrever os campos que não desejar atualizar
		if(subcategoria.getNomeSubcategoria() == null) {
			subcategoria.setNomeSubcategoria(subcategoriaDb.getNomeSubcategoria());
		}
		else {
			// Se o nome não for válido
			if(!isNomeValid(subcategoria.getNomeSubcategoria())) {
				throw new InvalidEntityException(
						"O nome não pode ter menos de 3 e mais de 45 "
						+ "caracteres");
			}
		}
		
		if(subcategoria.getDescricao() == null) {
			subcategoria.setDescricao(subcategoriaDb.getDescricao());
		}
		else {
			// Se a descrição não for válida
			if(!isDescValid(subcategoria.getDescricao())) {
				throw new InvalidEntityException(
						"A descrição não pode ter menos de 15 e mais de 255 "
						+ "caracteres");
			}
		}
		
		if(subcategoria.getCategoria() == null) {
			subcategoria.setCategoria(subcategoriaDb.getCategoria());
		}
		
		System.out.println("[ValidaSubcategoria] : end isValidUpdate");
		return subcategoria;
	}
}
