package com.springrecipes.controller;

import com.springrecipes.dto.IngredientDto;
import com.springrecipes.dto.RecipeDto;
import com.springrecipes.dto.UnitOfMeasureDto;
import com.springrecipes.service.IngredientService;
import com.springrecipes.service.RecipeService;
import com.springrecipes.service.UnitOfMeasureService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log4j2
@RestController
@RequestMapping("/api/recipes")
public class IngredientController {

	private final IngredientService ingredientService;
	private final RecipeService recipeService;
	private final UnitOfMeasureService unitOfMeasureService;


	public IngredientController(IngredientService ingredientService, RecipeService recipeService, UnitOfMeasureService unitOfMeasureService) {
		this.ingredientService = ingredientService;
		this.recipeService = recipeService;
		this.unitOfMeasureService = unitOfMeasureService;
	}

	@GetMapping("{id}/ingredients")
	public List<IngredientDto> getIngredientsByRecipeId(@PathVariable Long id){

		RecipeDto recipe = recipeService.findById(id);
		log.debug("Forwarded recipe: " +recipe);
		List<IngredientDto> ingredients = ingredientService.findByRecipeId(id);
		log.debug("Received request for obtaining ingredients contained in forwarded recipe " +ingredients);
		return ingredients;
	}

    @ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("{id}/ingredients")
	public IngredientDto createIngredient(@PathVariable Long id, @RequestBody IngredientDto ingredientDto) {

		log.debug("Received request for creating an ingredient");
		UnitOfMeasureDto unitOfMeasure = unitOfMeasureService.findById(ingredientDto.getUnitOfMeasure().getId());
		log.debug("Setting up unit of measure: " +unitOfMeasure);
		IngredientDto ingredient = ingredientService.save(ingredientDto);
		log.debug("Ingredient created " +ingredient);
		log.info("Ingredient created");
		return ingredient;
	}
	
	@PutMapping("{id}/ingredients")
	public IngredientDto updateIngredient(@PathVariable Long id, @RequestBody IngredientDto ingredientDto){

		log.debug("Received request for updating an ingredient");
		log.debug("Ingredient for update: " +ingredientService.findById(id));
		IngredientDto ingredient = ingredientService.saveIngredientByDto(id, ingredientDto);
		log.debug("Ingredient updated " +ingredientService.findById(id));
		log.info("Ingredient updated");
		return ingredient;
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}/ingredients")
	public void deleteIngredient(@PathVariable Long id){

		log.debug("Deleting recipe");
		ingredientService.deleteById(id);
		log.info("Recipe deleted");
	}
}
