package com.springrecipes.service;

import com.springrecipes.dto.IngredientDto;

import java.util.List;

public interface IngredientService {

	IngredientDto findById(Long id);

	List<IngredientDto> findByRecipeId(Long id);

	IngredientDto save(IngredientDto ingredientDto);

	IngredientDto saveIngredientByDto(Long id, IngredientDto ingredientDto);
	
	void deleteById(Long id);
}
