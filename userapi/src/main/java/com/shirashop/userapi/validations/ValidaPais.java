package com.shirashop.userapi.validations;

public class ValidaPais {

	/**
	 * Verifica se o código do País está em um formato
	 * adequado:
	 * +xxx ou +xx
	 * @param codigo Código do País
	 * @return retorna true caso esteja no formato adequado
	 */
	public static boolean isCodigoValid(String codigo) {
		
		if (codigo.matches("\\+\\d{3}") ||
			codigo.matches("\\+\\d{2}")) {
				return true;
		}
		
		return false;
	}
	
}
