package com.springrecipes.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springrecipes.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
