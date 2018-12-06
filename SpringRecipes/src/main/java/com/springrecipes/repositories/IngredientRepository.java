package com.springrecipes.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springrecipes.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long>{
	Optional<Ingredient> findByRecipeId(Long id);
}
