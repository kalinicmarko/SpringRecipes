package com.springrecipes.controller;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrecipes.exceptions.IngredientNotFoundException;
import com.springrecipes.exceptions.RecipeNotFoundException;
import com.springrecipes.model.Ingredient;
import com.springrecipes.repositories.IngredientRepository;
import com.springrecipes.service.IngredientService;
import com.springrecipes.service.RecipeService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api")
public class IngredientController {

	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	private final IngredientRepository ingredientRepository;
	

	

	public IngredientController(RecipeService recipeService, IngredientService ingredientService,
			IngredientRepository ingredientRepository) {
		super();
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.ingredientRepository = ingredientRepository;
	}

	@GetMapping("recipes/{id}/ingredients")
	public Optional<Ingredient> getIngredientsByRecipeId(@PathVariable Long id){
		Optional<Ingredient> ingredientsOptional = ingredientService.findByRecipeId(id);
		return ingredientsOptional;
	}
	
	@PostMapping("recipes/{id}/ingredients")
	public Optional<Ingredient> createIngredient(@PathVariable Long id, @RequestBody Ingredient ingredient) {
		
		Optional<Ingredient> ingredientOptional = recipeService.findById(id).map(recipe -> {
			ingredient.setRecipe(recipe);
            return ingredientService.save(ingredient);
        });
		
		return ingredientOptional;
	}
	
	@PutMapping("recipes/{id}/ingredients/{ingredientId}")
	public Optional<Ingredient> updateIngredient(@PathVariable Long id, 
			@PathVariable Long ingredientId, @RequestBody Ingredient ingredientUpdated) throws NotFoundException{
		Optional<Ingredient> ingredientOptional = ingredientService.findById(ingredientId).map(ingredient -> {
			ingredient.setName(ingredientUpdated.getName());
			ingredient.setAmount(ingredientUpdated.getAmount());
			ingredient.setRecipe(ingredientUpdated.getRecipe());
			ingredient.setUom(ingredientUpdated.getUom());
			return ingredientService.save(ingredient);
		});
		if(!ingredientOptional.isPresent()) {
			throw new IngredientNotFoundException("id-" +id);
		}
		return ingredientOptional;
	}
	
	@DeleteMapping("recipes/{id}/ingredients/{ingredientId}")
	public Optional<Object> deleteIngredient(@PathVariable Long id, @PathVariable Long ingredientId){
		if(!recipeService.findById(id).isPresent()) {
			throw new RecipeNotFoundException("id-" +id);
		}
		
		Optional<Object> ingredientOptional = ingredientService.findById(ingredientId).map(ingredient -> {
			ingredientRepository.delete(ingredient);
			return ResponseEntity.ok().build();
		});
		return ingredientOptional;
	}
}
