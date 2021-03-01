package com.assignment.recipemanagement.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.assignment.recipemanagement.entity.Ingredient;
import com.assignment.recipemanagement.exception.RecipeManagementException;
import com.assignment.recipemanagement.repository.IngredientRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class IngredientServiceTest {

	@MockBean
	IngredientRepository ingredientRepository;

	@InjectMocks
	IngredientService ingredientService;

	@Test
	public void findByIngredientIdTestWhenIngredientIdIsFound() {
		Ingredient mockIngredient = new Ingredient();
		mockIngredient.setIngredientName("honey");
		mockIngredient.setIngredientId(new Long(1));

		when(ingredientRepository.findByIngredientId(Mockito.anyLong())).thenReturn(mockIngredient);
		Ingredient responseIngredient = ingredientRepository.findByIngredientId(new Long(1));
		assertThat(responseIngredient).isNotNull();

	}
	
    @Test
	public void findByIngredientIdTestWhenIngredientIdIsNotFound() {
		Ingredient mockIngredient = new Ingredient();
		mockIngredient.setIngredientName("honey");
		mockIngredient.setIngredientId(new Long(1));

		when(ingredientRepository.findByIngredientId(Mockito.anyLong())).thenReturn(null);
		RecipeManagementException exception = assertThrows(RecipeManagementException.class,
				() -> ingredientService.findByIngredientId(new Long(1)));
		assertEquals("Ingredient not found for IngredientId 1", exception.getErrorMessage());

	}

}
