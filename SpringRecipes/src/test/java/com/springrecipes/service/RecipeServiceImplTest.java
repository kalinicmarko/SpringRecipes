package com.springrecipes.service;

import com.springrecipes.dto.RecipeDto;
import com.springrecipes.mapper.RecipeMapper;
import com.springrecipes.model.Recipe;
import com.springrecipes.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, RecipeMapper.INSTANCE);
    }

    @Test
    public void getRecipes() {

        //given
        List<Recipe> recipes = Arrays.asList(new Recipe());

        when(recipeRepository.findAll()).thenReturn(recipes);

        //when
        List<RecipeDto> recipesDtos = recipeService.getRecipes();

        //then
        assertEquals(1, recipesDtos.size());
    }

    @Test
    public void findById() {

        //given
        Recipe recipe = new Recipe();
        recipe.setId(2L);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //when
        RecipeDto recipeDto = recipeService.findById(2L);

        //then
        assertNotNull("Null recipe returned", recipeDto);
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void save() {

        //given
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setName("recipe");

        Recipe savedRecipe = new Recipe();
        savedRecipe.setName(recipeDto.getName());

        when(recipeRepository.save(any(Recipe.class))).thenReturn(savedRecipe);

        //when
        RecipeDto savedDto = recipeService.save(recipeDto);

        //then
        assertEquals(recipeDto.getName(), savedDto.getName());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    public void saveRecipeByDto() {

        //given
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(1L);
        recipeDto.setName("recipe");

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId(recipeDto.getId());
        savedRecipe.setName(recipeDto.getName());

        when(recipeRepository.save(any(Recipe.class))).thenReturn(savedRecipe);

        //when
        RecipeDto savedDto = recipeService.saveRecipeByDto(1L, recipeDto);

        //then
        assertEquals(recipeDto.getId(), savedDto.getId());
        assertEquals(recipeDto.getName(), savedDto.getName());
    }

    @Test
    public void deleteById() {

        Long idToDelete = 1L;
        recipeRepository.deleteById(idToDelete);
        verify(recipeRepository, times(1)).deleteById(anyLong());

    }
}