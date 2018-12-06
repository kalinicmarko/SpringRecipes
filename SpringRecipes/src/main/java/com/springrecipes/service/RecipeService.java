package com.springrecipes.service;

import java.util.Optional;
import java.util.Set;

import com.springrecipes.model.Recipe;

public interface RecipeService {
	Set<Recipe> getRecipes();
	
	Optional<Recipe> findById(Long id);
	
	void deleteById(Long id);
	
	Recipe save(Recipe recipe);
}
