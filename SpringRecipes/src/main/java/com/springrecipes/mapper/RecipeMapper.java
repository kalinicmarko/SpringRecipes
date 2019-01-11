package com.springrecipes.mapper;

import com.springrecipes.dto.RecipeDto;
import com.springrecipes.model.Recipe;
import org.mapstruct.Mapper;

@Mapper
public interface RecipeMapper {

	RecipeDto recipeToRecipeDto(Recipe recipe);
	Recipe recipeDtoToRecipe(RecipeDto recipeDto);
}
