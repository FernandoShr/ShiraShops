package com.shirashop.productapi.validations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import com.shirashop.productapi.dto.ProductModel;
import com.shirashop.productapi.entities.Product;
import com.shirashop.productapi.exceptions.InvalidEntityException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidaProduto {
	
	/**
	 * Verifica se o nome do produto obedece o limite de caracteres:
	 * entre 2 a 45 caracteres
	 * @param nomeProduto nome do produto
	 * @return retorna true caso obedeça o limite de caracteres
	 */
	public static boolean isNameValid(String nomeProduto) {
	
		if (nomeProduto.length() < 2 || nomeProduto.length() > 45 ) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Verifica se a descrição obedece o limite de caracteres: 
	 * entre 15 a 255 caracteres
	 * @param descricao Descrição do produto
	 * @return retorna true caso seja válido
	 */
	public static boolean isDescValid(String descricao) {
		
		if (descricao.length() < 15 || descricao.length() > 255 ) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Verifica se o valor do sku é no mínimo 1
	 * @param sku SKU do produto
	 * @return retorna true caso seja válido
	 */
	public static boolean isSkuValid(int sku) {
		
		if (sku <= 0) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Verifica se o estoque não apresenta um valor negativo
	 * @param estoque Estoque do produto
	 * @return retorna true caso seja válido
	 */
	public static boolean isEstoqueValid(int estoque) {
		// Se estoque tiver valor negativo
		if (estoque < 0) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Verifica se o preço do produto não é negativo
	 * @param valor_unitario Valor do produto
	 * @return retorna true caso seja válido
	 */
	public static boolean isValorValid(double valor_unitario) {
		
		if (valor_unitario <= 0) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Valida os campos de produto para ser registrado no banco de dados
	 * @param product Produto a ser registrado
	 */
	public static void isValidCreate(Product product) {
		log.info("[ValidaProduto] : in isValidCreate (Product)");
		
		// Valida campos obrigatórios
		ValidaCampoNulo.isNull(product);
		
		// **** VERIFICA NOME ****
		// Se o nome não for válido
		if(!isNameValid(product.getNomeProduto())) {
			throw new InvalidEntityException(
					("Ops! O nome do produto deve ter entre 2 e 45 caracteres."
							+ " Verifique e tente novamente."));
		}
		// **** VERIFICA DESCRIÇÃO ****
		// Se a descrição não for válida
		if(!isDescValid(product.getDescricao())) {
			throw new InvalidEntityException(
					"Poxa vida! A descrição está fora do tamanho esperado. "
					+ "Digite de 15 a 255 caracteres.");
		}
		
		// **** VERIFICA SKU ****
		// Se sku não for válido
		if(!isSkuValid(product.getSku())) {
			throw new InvalidEntityException(
					"O valor mínimo "
					+ "para o campo sku é 1.");
		}
		
		// **** VERIFICA ESTOQUE ****
		// Se o estoque não for válido
		if (!isEstoqueValid(product.getEstoque())) {
			throw new InvalidEntityException(
					"Ops! Quantidade inválida, digite um número que não seja negativo.");
		}
		
		// **** VERFICIA VALOR ****
		// Se o valor do produto não for válido
		if (!isValorValid(product.getValor_unitario())) {
			throw new InvalidEntityException(
					"O valor não pode ser igual ou inferior a R$ 0,00");
		}
		// Arredonda o valor para 2 casas decimais
		BigDecimal bd = new BigDecimal(product.getValor_unitario());
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		product.setValor_unitario(bd.doubleValue());
		
		
		log.info("[ValidaProduto] : end isValidCreate (Product)");
	}
	/**
	 * Valida os campos de produto para ser registrado no banco de dados
	 * @param product Produto a ser registrado
	 */
	public static void isValidCreate(ProductModel product) {
		log.info("[ValidaProduto] : in isValidCreate (ProductModel)");
		
		// Valida campos obrigatórios
		ValidaCampoNulo.isNull(product);
		
		// **** VERIFICA NOME ****
		// Se o nome não for válido
		if(!isNameValid(product.getNomeProduto())) {
			throw new InvalidEntityException(
					("Ops! O nome do produto deve ter entre 2 e 45 caracteres."
							+ " Verifique e tente novamente."));
		}
		// **** VERIFICA DESCRIÇÃO ****
		// Se a descrição não for válida
		if(!isDescValid(product.getDescricao())) {
			throw new InvalidEntityException(
					"Poxa vida! A descrição está fora do tamanho esperado. "
							+ "Digite de 15 a 255 caracteres.");
		}
		
		// **** VERIFICA SKU ****
		// Se sku não for válido
		if(!isSkuValid(product.getSku())) {
			throw new InvalidEntityException(
					"O valor mínimo "
							+ "para o campo sku é 1.");
		}
		
		// **** VERIFICA ESTOQUE ****
		// Se o estoque não for válido
		if (!isEstoqueValid(product.getEstoque())) {
			throw new InvalidEntityException(
					"Ops! Quantidade inválida, digite um número que não seja negativo.");
		}
		
		// **** VERFICIA VALOR ****
		// Se o valor do produto não for válido
		if (!isValorValid(product.getValor_unitario())) {
			throw new InvalidEntityException(
					"O valor não pode ser igual ou inferior a R$ 0,00");
		}
		// Arredonda o valor para 2 casas decimais
		BigDecimal bd = new BigDecimal(product.getValor_unitario());
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		product.setValor_unitario(bd.doubleValue());
		
		
		log.info("[ValidaProduto] : end isValidCreate (ProductModel)");
	}
	
	/**
	 * Valida os campos para atualizar o produto no banco de dados
	 * @param productDb Produto a ser atualizado no banco de dados
	 * @param product Produto com as informações a serem alteradas
	 * @return O objeto com as informações atualizadas
	 */
	public static Product isValidUpdate(Product productDb, Product product) {
		log.info("[ValidaProduto] : in isValidUpdate");
		
		// VALIDAÇÕES
		/* Verifica nulo para assim não ser necessário reescrever 
		 o que não se deseja alterar*/
		
		// *** NOME PRODUTO ***
		if(product.getNomeProduto() == null) {
			product.setNomeProduto(productDb.getNomeProduto());
		}
		else {
			if(!isNameValid(product.getNomeProduto())) {
				throw new InvalidEntityException(
						("Ops! O nome do produto deve ter entre 2 e 45 caracteres."
								+ " Verifique e tente novamente."));
			}
		}
		
		// *** DESCRIÇÃO PRODUTO ***
		if(product.getDescricao() == null) {
			product.setDescricao(productDb.getDescricao());
		}
		else {
			if(!isDescValid(product.getDescricao())) {
				throw new InvalidEntityException(
						"Poxa vida! A descrição está fora do tamanho esperado. "
						+ "Digite de 15 a 255 caracteres.");
			}		
		}
		
		// *** ESTOQUE ***
		try {
			if(Objects.isNull(product.getEstoque())) {
				product.setEstoque(productDb.getEstoque());
			}
			else {
				// Se o estoque não for válido
				if (!isEstoqueValid(product.getEstoque())) {
					throw new InvalidEntityException(
							"Ops! Quantidade inválida, digite um número que não seja negativo.");
				}
			}
		} catch (NullPointerException e) {
			product.setEstoque(productDb.getEstoque());
		}
		
		// *** SKU ***
		try {
			if(Objects.isNull(product.getSku())) {
				product.setSku(productDb.getSku());
			}
			else {
				// Se sku não for válido
				if(!isSkuValid(product.getSku())) {
					throw new InvalidEntityException(
							"O valor mínimo "
							+ "para o campo sku é 1.");
				}
			}
		}catch (NullPointerException e) {
			product.setSku(productDb.getSku());
		}
		
		// *** VALOR ***
		try {
			if(Objects.isNull(product.getValor_unitario())) {
				product.setValor_unitario(productDb.getValor_unitario());
			}
			else {
				// Se o valor do produto não for válido
				if (!isValorValid(product.getValor_unitario())) {
					throw new InvalidEntityException(
							"O valor não pode ser igual ou inferior a R$ 0,00");
				}
				// Arredonda o valor para 2 casas decimais
				BigDecimal bd = new BigDecimal(product.getValor_unitario());
				bd = bd.setScale(2, RoundingMode.HALF_UP);
				product.setValor_unitario(bd.doubleValue());
			}
		}catch (NullPointerException e) {
			product.setValor_unitario(productDb.getValor_unitario());
		}
		

		
		log.info("[ValidaProduto] : end isValidUpdate");
		return product;
	}
}
