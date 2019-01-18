package com.springrecipes.mapper;

import com.springrecipes.dto.IngredientDto;
import com.springrecipes.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper {

    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);
    IngredientDto ingredientToIngredientDto(Ingredient ingredient);
    Ingredient ingredientDtoToIngredient(IngredientDto ingredientDto);
}
