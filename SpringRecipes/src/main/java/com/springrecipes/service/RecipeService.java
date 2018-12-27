package com.springrecipes.service;

import com.springrecipes.dto.RecipeDto;

import java.util.List;

public interface RecipeService {
	List<RecipeDto> getRecipes();
	
	RecipeDto findById(Long id);
	
	RecipeDto save(RecipeDto recipeDto);

	RecipeDto saveRecipeByDto(Long id, RecipeDto recipeDto);

	void deleteById(Long id);
}
