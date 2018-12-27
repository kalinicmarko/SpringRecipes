package com.springrecipes.controller;

import com.springrecipes.dto.RecipeDto;
import com.springrecipes.exceptions.RecipeNotFoundException;
import com.springrecipes.service.RecipeService;
import lombok.extern.log4j.Log4j2;
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

		List<RecipeDto> recipes = recipeService.getRecipes();
		log.debug("getAll() " +recipes);
		return recipes;
	}
	
	@GetMapping("{id}")
	public RecipeDto getRecipeById(@PathVariable Long id) {

		RecipeDto recipe = recipeService.findById(id);
		log.debug("getRecipeById() " +recipe);
		return recipe;
	}

	@PostMapping
	public RecipeDto createRecipe(@RequestBody RecipeDto recipeDto){

		RecipeDto recipe = recipeService.save(recipeDto);
		log.debug("Recipe created: " +recipe);
		log.info("Recipe created");
		return recipe;
	}
	
	@PutMapping("{id}")
	public RecipeDto updateRecipe(@RequestBody RecipeDto recipeDto, @PathVariable Long id) {

		RecipeDto recipe = recipeService.saveRecipeByDto(id, recipeDto);
		log.debug("Updated recipe: " +recipe);
		log.info("Recipe updated");
		return recipe;
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		try {
			recipeService.deleteById(id);
		} catch (Exception e) {
			log.warn(e.getMessage());
			throw new RecipeNotFoundException("Recipe not found");
		}
	}
}
