package com.springrecipes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.springrecipes.dto.RecipeDto;
import com.springrecipes.model.Recipe;

@Mapper
public interface RecipeMapper {
	
	RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);
	RecipeDto recipeToRecipeDto(Recipe recipe);
	Recipe recipeDtoToRecipe(RecipeDto recipeDto);
}
