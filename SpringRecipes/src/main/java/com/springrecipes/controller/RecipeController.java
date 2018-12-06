package com.springrecipes.controller;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springrecipes.exceptions.RecipeNotFoundException;
import com.springrecipes.model.Recipe;
import com.springrecipes.service.RecipeService;

@RestController
@RequestMapping("/api")
public class RecipeController {
	
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}
	
	@GetMapping("recipes")
	public Set<Recipe> getAll(){
		return recipeService.getRecipes();
	}
	
	@GetMapping("recipes/{id}")
	public Optional<Recipe> getRecipeById(@PathVariable Long id) {
		
		Optional<Recipe> recipe = recipeService.findById(id);
		
		if(!recipe.isPresent()) {
			throw new RecipeNotFoundException("id-" +id);
		}
		return recipe;
	}
	
	@PostMapping("/recipes")
	public ResponseEntity<Object> createRecipe(@RequestBody Recipe recipe) {
		Recipe savedRecipe = recipeService.save(recipe);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedRecipe.getId()).toUri();		
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/recipes/{id}")
	public Optional<Recipe> updateRecipe(@RequestBody Recipe recipeUpdated, @PathVariable Long id) {
		
		Optional<Recipe> recipeOptional = recipeService.findById(id).map(recipe -> {
			recipe.setDescription(recipeUpdated.getDescription());
			recipe.setName(recipeUpdated.getName());
			recipe.setCookTime(recipeUpdated.getCookTime());
			recipe.setDifficulty(recipeUpdated.getDifficulty());
			recipe.setPropTime(recipeUpdated.getPropTime());
			recipe.setNote(recipeUpdated.getNote());
			recipe.setCategories(recipeUpdated.getCategories());
			return recipeService.save(recipe);
		});
		
		if(!recipeOptional.isPresent()) {
			throw new RecipeNotFoundException("id-" +id);
		}
		return recipeOptional;
	}
	
	@DeleteMapping("recipes/{id}")
	public void delete(@PathVariable Long id) {
		recipeService.deleteById(id);
	}
}
