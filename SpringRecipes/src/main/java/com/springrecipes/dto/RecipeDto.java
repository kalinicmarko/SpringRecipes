package com.springrecipes.dto;

import com.springrecipes.model.Difficulty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
public class RecipeDto {

	Long id;
	String name;
	String description;
	Integer propTime;
	Integer cookTime;
	NoteDto note;
	Difficulty dificulty;
	List<IngredientDto> ingredients;
	List<CategoryDto> categories;
	
}
