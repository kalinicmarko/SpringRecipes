package com.springrecipes.service;

import com.springrecipes.dto.IngredientDto;
import com.springrecipes.mapper.IngredientMapper;
import com.springrecipes.model.Ingredient;
import com.springrecipes.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngredientRepository ingredientRepository;
	private final IngredientMapper ingredientMapper;

	public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
		this.ingredientRepository = ingredientRepository;
		this.ingredientMapper = ingredientMapper;
	}

	@Override
	public List<IngredientDto> findByRecipeId(Long id) {
		return ingredientRepository.findByRecipeId(id).stream().map(ingredientMapper::ingredientToIngredientDto).collect(Collectors.toList());
	}

	@Override
	public IngredientDto save(IngredientDto ingredientDto) {

		Ingredient ingredient = ingredientMapper.ingredientDtoToIngredient(ingredientDto);
		Ingredient savedIngredient = ingredientRepository.save(ingredient);

		return ingredientMapper.ingredientToIngredientDto(savedIngredient);
	}

	@Override
	public IngredientDto saveIngredientByDto(Long id, IngredientDto ingredientDto) {

		Ingredient ingredient = ingredientMapper.ingredientDtoToIngredient(ingredientDto);
		ingredient.setId(id);

		Ingredient savedIngredient = ingredientRepository.save(ingredient);

		return ingredientMapper.ingredientToIngredientDto(savedIngredient);
	}


	@Override
	public void deleteById(Long id) {
		ingredientRepository.deleteById(id);
	}

}
