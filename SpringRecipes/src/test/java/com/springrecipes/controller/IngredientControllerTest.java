package com.springrecipes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrecipes.dto.IngredientDto;
import com.springrecipes.dto.RecipeDto;
import com.springrecipes.dto.UnitOfMeasureDto;
import com.springrecipes.model.UnitOfMeasure;
import com.springrecipes.service.IngredientService;
import com.springrecipes.service.RecipeService;
import com.springrecipes.service.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    @InjectMocks
    IngredientController ingredientController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    public void getIngredientsByRecipeId() throws Exception {

        RecipeDto recipe = new RecipeDto();
        recipe.setId(1L);

        IngredientDto ingredient1 = new IngredientDto();
        ingredient1.setId(1L);
        ingredient1.setName("Ingredient 1");

        IngredientDto ingredient2 = new IngredientDto();
        ingredient2.setId(2L);
        ingredient2.setName("Ingredient 2");

        List<IngredientDto> ingredients = Arrays.asList(ingredient1, ingredient2);

        when(ingredientService.findByRecipeId(1L)).thenReturn(ingredients);

        mockMvc.perform(get("/api/recipes/1/ingredients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Ingredient 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Ingredient 2")));
        assertEquals(2, ingredients.size());
    }

    @Test
    public void createIngredient() throws  Exception {

        UnitOfMeasureDto unitOfMeasure = new UnitOfMeasureDto();
        unitOfMeasure.setId(1L);
        unitOfMeasure.setName("UnitOfMeasure");

        IngredientDto ingredient = new IngredientDto();
        ingredient.setId(1L);
        ingredient.setName("Ingredient");

        IngredientDto returnedIngredient = new IngredientDto();
        returnedIngredient.setId(1L);
        returnedIngredient.setName("Returned ingredient");

        when(unitOfMeasureService.findById(1L)).thenReturn(unitOfMeasure);
        when(ingredientService.save(ingredient)).thenReturn(returnedIngredient);

        mockMvc.perform(post("/api/recipes/1/ingredients")
                .contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void updateIngredient() throws Exception {

        IngredientDto ingredient = new IngredientDto();
        ingredient.setId(1L);
        ingredient.setName("Ingredient");

        IngredientDto returnedIngredient = new IngredientDto();
        returnedIngredient.setId(ingredient.getId());
        returnedIngredient.setName(ingredient.getName());

        when(ingredientService.saveIngredientByDto(1L, ingredient)).thenReturn(returnedIngredient);

        mockMvc.perform(put("/api/recipes/1/ingredients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ingredient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Ingredient")));
    }


    @Test
    public void deleteIngredient() throws Exception {

        mockMvc.perform(delete("/api/recipes/1/ingredients")
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