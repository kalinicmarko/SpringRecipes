package com.springrecipes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecipeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 2712003412785097453L;

	public RecipeNotFoundException(String exception) {
		super(exception);
	}
}
