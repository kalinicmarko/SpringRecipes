package com.springrecipes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IngredientNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = 1043090141525570999L;

	public IngredientNotFoundException(String exception) {
		super(exception);
	}
}
