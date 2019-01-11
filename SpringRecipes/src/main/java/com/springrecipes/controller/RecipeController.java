package com.springrecipes.controller;

import com.springrecipes.dto.RecipeDto;
import com.springrecipes.service.RecipeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}
	
	@GetMapping
	public List<RecipeDto> getAll(){

		log.debug("Received request for obtaining all recipes");
		List<RecipeDto> recipes = recipeService.getRecipes();
		log.debug("All recipes returned: " +recipes);
		log.info("Recipes returned");
		return recipes;
	}
	
	@GetMapping("{id}")
	public RecipeDto getRecipeById(@PathVariable Long id) {

		log.debug("Received request for obtaining Recipe by forwarded id");
		RecipeDto recipe = recipeService.findById(id);
		log.debug("Recipe found: " +recipe);
		log.info("Recipe found");
		return recipe;
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public RecipeDto createRecipe(@RequestBody RecipeDto recipeDto){

		log.debug("Received request for creating recipe");
		RecipeDto recipe = recipeService.save(recipeDto);
		log.debug("Recipe created: " +recipe);
		log.info("Recipe created");
		return recipe;
	}
	
	@PutMapping("{id}")
	public RecipeDto updateRecipe(@RequestBody RecipeDto recipeDto, @PathVariable Long id) {

		log.debug("Received request for updating recipe");
		RecipeDto recipe = recipeService.saveRecipeByDto(id, recipeDto);
		log.debug("Updated recipe: " +recipe);
		log.info("Recipe updated");
		return recipe;
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.debug("Deleting recipe");
		recipeService.deleteById(id);
		log.info("Recipe deleted");
	}
}
