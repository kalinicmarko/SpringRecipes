package com.springrecipes.mapper;

import com.springrecipes.dto.IngredientDto;
import com.springrecipes.model.Ingredient;
import org.mapstruct.Mapper;

@Mapper
public interface IngredientMapper {

    IngredientDto ingredientToIngredientDto(Ingredient ingredient);
    Ingredient ingredientDtoToIngredient(IngredientDto ingredientDto);
}
