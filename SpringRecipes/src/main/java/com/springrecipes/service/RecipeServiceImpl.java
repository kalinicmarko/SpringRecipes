package com.springrecipes.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.springrecipes.exceptions.RecipeNotFoundException;
import com.springrecipes.model.Recipe;
import com.springrecipes.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

	@Override
	public Optional<Recipe> findById(Long id) {
		Optional<Recipe> recipeOptional =  recipeRepository.findById(id);
		return recipeOptional;
	}

	@Override
	public void deleteById(Long id) {
		recipeRepository.deleteById(id);
	}

	@Override
	public Recipe save(Recipe recipe) {
		return recipeRepository.save(recipe);
	}
}
