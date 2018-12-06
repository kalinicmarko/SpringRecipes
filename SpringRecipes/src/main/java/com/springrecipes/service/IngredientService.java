package com.springrecipes.service;

import java.util.Optional;

import com.springrecipes.model.Ingredient;

public interface IngredientService {

	Ingredient save(Ingredient ingredient);
	
	Optional<Ingredient> findById(Long id);
	
	void delete(Ingredient ingredient);
	
	Optional<Ingredient> findByRecipeId(Long id);

}
