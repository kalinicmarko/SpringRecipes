package com.springrecipes.controller;

import com.springrecipes.dto.IngredientDto;
import com.springrecipes.exceptions.RecipeNotFoundException;
import com.springrecipes.service.IngredientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log4j2
@RestController
@RequestMapping("/api/recipes")
public class IngredientController {

	private final IngredientService ingredientService;
	

	public IngredientController(IngredientService ingredientService) {
		super();
		this.ingredientService = ingredientService;
	}

	@GetMapping("{id}/ingredients")
	public List<IngredientDto> getIngredientsByRecipeId(@PathVariable Long id){

		List<IngredientDto> ingredients = ingredientService.findByRecipeId(id);
		log.debug("getIngredientsByRecipeId() " +ingredients);
		if(ingredients.isEmpty()){
			throw new RecipeNotFoundException("Recipe not found");
		}
		return ingredients;
	}

	@PostMapping("{id}/ingredients")
	public IngredientDto createIngredient(@PathVariable Long id, @RequestBody IngredientDto ingredientDto) {

		IngredientDto ingredient = ingredientService.save(ingredientDto);
		log.debug("Ingredient created " +ingredient);
		log.info("Ingredient created");
		return ingredient;
	}
	
	@PutMapping("{id}/ingredients")
	public IngredientDto updateIngredient(@PathVariable Long id, @RequestBody IngredientDto ingredientDto){

		IngredientDto ingredient = ingredientService.saveIngredientByDto(id, ingredientDto);
		return ingredient;
	}
	
	@DeleteMapping("{id}/ingredients")
	public void deleteIngredient(@PathVariable Long id){
		try {
			ingredientService.deleteById(id);
		}catch (Exception e){
			log.warn(e.getMessage());
			throw new RecipeNotFoundException("Recipe not found");
		}

	}
}
