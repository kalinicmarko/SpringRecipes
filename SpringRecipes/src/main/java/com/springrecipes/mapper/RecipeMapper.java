package com.springrecipes.mapper;

import com.springrecipes.dto.RecipeDto;
import com.springrecipes.model.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMapper {

	RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);
	RecipeDto recipeToRecipeDto(Recipe recipe);
	Recipe recipeDtoToRecipe(RecipeDto recipeDto);
}
