package com.shirashop.productapi.validations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;

public class ValidaCampoNulo {
	/**
	 * Verifica se os campos que são obrigatórios não estão nulos
	 * @param obj Recebe um objeto qualquer
	 */
	public static void isNull(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
        List<String> nullFields = new ArrayList<>();

        for (int i = 1; i < fields.length; i++) {
        	Field field = fields[i];
        	// Permite o acesso aos campos
            field.setAccessible(true);
            try {
            	// Verifica a anotação @Column
                if (field.get(obj) == null && !field.isAnnotationPresent(Column.class)) {
                    nullFields.add(field.getName());
                } else if (field.get(obj) == null && field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (!columnAnnotation.nullable()) {
                        nullFields.add(field.getName());
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (!nullFields.isEmpty()) {
        	if (nullFields.size() > 1) {
	            throw new NullPointerException(
	            		"Não foi possível cadastrar o produto. "
	        					+ "Os campos " + String.join(", ", nullFields) + " são obrigatórios. "
	        					+ "Verifique e tente novamente." );
            }else {
	            throw new NullPointerException(
	            		"Não foi possível cadastrar o produto. "
	        					+ "O campo " + String.join(", ", nullFields) + " é obrigatório. "
	        					+ "Verifique e tente novamente." );     
            }
        }
	}
}
