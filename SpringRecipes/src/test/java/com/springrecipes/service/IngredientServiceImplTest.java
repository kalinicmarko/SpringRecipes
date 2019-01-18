package com.springrecipes.service;

import com.springrecipes.dto.IngredientDto;
import com.springrecipes.mapper.IngredientMapper;
import com.springrecipes.model.Ingredient;
import com.springrecipes.repositories.IngredientRepository;
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

public class IngredientServiceImplTest {

    IngredientServiceImpl ingredientService;

    @Mock
    IngredientRepository ingredientRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientRepository, IngredientMapper.INSTANCE);
    }

    @Test
    public void findById() {

        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);

        Optional<Ingredient> ingredientOptional = Optional.of(ingredient);

        when(ingredientRepository.findById(anyLong())).thenReturn(ingredientOptional);

        //when
        IngredientDto ingredientDto = ingredientService.findById(1L);

        //then
        assertNotNull("Null ingredient returned", ingredientDto);
        verify(ingredientRepository, times(1)).findById(anyLong());
    }

    @Test
    public void findByRecipeId() {

        //given
        List<Ingredient> ingredients = Arrays.asList(new Ingredient());

        when(ingredientRepository.findByRecipeId(anyLong())).thenReturn(ingredients);

        //when
        List<IngredientDto> ingredientDtos = ingredientService.findByRecipeId(anyLong());

        //then
        assertEquals(1, ingredientDtos.size());
    }

    @Test
    public void save() {

        //given
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(1L);
        ingredientDto.setName("ingredient");

        Ingredient savedIngredient = new Ingredient();
        savedIngredient.setId(1L);
        savedIngredient.setName("ingredient");

        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(savedIngredient);

        //when
        IngredientDto savedDto = ingredientService.save(ingredientDto);

        //then
        assertEquals(ingredientDto.getId(), savedDto.getId());
        assertEquals(ingredientDto.getName(), savedDto.getName());
        verify(ingredientRepository, times(1)).save(any(Ingredient.class));
    }

    @Test
    public void saveIngredientByDto() {

        //given
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(1L);
        ingredientDto.setName("ingredient");

        Ingredient savedIngredient = new Ingredient();
        savedIngredient.setId(1L);
        savedIngredient.setName("ingredient");

        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(savedIngredient);

        //when
        IngredientDto savedDto = ingredientService.saveIngredientByDto(1L, ingredientDto);

        //then
        assertEquals(ingredientDto.getId(), savedDto.getId());
        verify(ingredientRepository, times(1)).save(any(Ingredient.class));

    }

    @Test
    public void deleteById() {

        Long idToDelete = 1L;

        ingredientRepository.deleteById(idToDelete);

        verify(ingredientRepository, times(1)).deleteById(anyLong());
    }
}