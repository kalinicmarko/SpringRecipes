package com.springrecipes.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springrecipes.model.Ingredient;
import com.springrecipes.repositories.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngredientRepository ingredientRepository;
	
	public IngredientServiceImpl(IngredientRepository ingredientRepository) {
		super();
		this.ingredientRepository = ingredientRepository;
	}


	@Override
	public Ingredient save(Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}


	@Override
	public Optional<Ingredient> findById(Long id) {
		Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);
		return ingredientOptional;
	}


	@Override
	public void delete(Ingredient ingredient) {
		ingredientRepository.delete(ingredient);
	}


	@Override
	public Optional<Ingredient> findByRecipeId(Long id) {
		Optional<Ingredient> ingredientOptional = ingredientRepository.findByRecipeId(id);
		return ingredientOptional;
	}
	
}
