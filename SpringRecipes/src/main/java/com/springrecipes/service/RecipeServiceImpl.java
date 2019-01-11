package com.springrecipes.service;

import com.springrecipes.dto.RecipeDto;
import com.springrecipes.exceptions.RecipeNotFoundException;
import com.springrecipes.mapper.RecipeMapper;
import com.springrecipes.model.Recipe;
import com.springrecipes.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeMapper recipeMapper;


	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeMapper recipeMapper) {
		super();
		this.recipeRepository = recipeRepository;
		this.recipeMapper = recipeMapper;
	}

	@Transactional(readOnly = true)
	@Override
	public List<RecipeDto> getRecipes() {
		return recipeRepository.findAll().stream().map(recipeMapper::recipeToRecipeDto).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	@Override
	public RecipeDto findById(Long id) {
		return recipeRepository.findById(id)
				.map(recipeMapper::recipeToRecipeDto)
				.orElseThrow(() -> new RecipeNotFoundException("Recipe not found"));
	}

	@Transactional
	@Override
	public RecipeDto save(RecipeDto recipeDto) {
		Recipe recipe = recipeMapper.recipeDtoToRecipe(recipeDto);
		Recipe savedRecipe = recipeRepository.save(recipe);

		return recipeMapper.recipeToRecipeDto(savedRecipe);
	}

	@Transactional
	@Override
	public RecipeDto saveRecipeByDto(Long id, RecipeDto recipeDto) {

		Recipe recipe = recipeMapper.recipeDtoToRecipe(recipeDto);
		recipe.setId(id);

		Recipe savedRecipe = recipeRepository.save(recipe);

		return recipeMapper.recipeToRecipeDto(savedRecipe);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		findById(id);
		recipeRepository.deleteById(id);
	}
}
