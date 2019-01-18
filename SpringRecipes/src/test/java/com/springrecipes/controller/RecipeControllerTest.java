package com.springrecipes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrecipes.dto.RecipeDto;
import com.springrecipes.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    @InjectMocks
    RecipeController recipeController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    public void getAll() throws Exception {

        RecipeDto recipe1 = new RecipeDto();
        recipe1.setId(1L);
        recipe1.setName("Marko");

        RecipeDto recipe2 = new RecipeDto();
        recipe2.setId(1L);
        recipe2.setName("Vlada");

        List<RecipeDto> recipes = Arrays.asList(recipe1, recipe2);

        when(recipeService.getRecipes()).thenReturn(recipes);

        mockMvc.perform(get("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Marko")))
                .andExpect(jsonPath("$[1].id", is(1)))
                .andExpect(jsonPath("$[1].name", is("Vlada")));
    }

    @Test
    public void getRecipeById() throws Exception {

        RecipeDto recipe = new RecipeDto();
        recipe.setName("Recipe");

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/api/recipes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Recipe")));
    }

    @Test
    public void createRecipe() throws Exception {

        RecipeDto recipe = new RecipeDto();
        recipe.setName("Recipe");

        RecipeDto returnDto = new RecipeDto();
        returnDto.setName(recipe.getName());

        when(recipeService.save(recipe)).thenReturn(returnDto);

        mockMvc.perform(post("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recipe)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Recipe")));
    }

    @Test
    public void updateRecipe() throws Exception {

        RecipeDto recipe = new RecipeDto();
        recipe.setName("Recipe");

        RecipeDto returnDto = new RecipeDto();
        returnDto.setName(recipe.getName());

        when(recipeService.saveRecipeByDto(1L, recipe)).thenReturn(returnDto);

        mockMvc.perform(put("/api/recipes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recipe)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Recipe")));
    }

    @Test
    public void delete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/recipes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}