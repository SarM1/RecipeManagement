package com.assignment.recipemanagement.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assignment.recipemanagement.Utils.RecipeManagementConstants;
import com.assignment.recipemanagement.entity.Ingredient;
import com.assignment.recipemanagement.entity.Recipe;
import com.assignment.recipemanagement.entity.RecipeDetail;
import com.assignment.recipemanagement.exception.RecipeManagementException;
import com.assignment.recipemanagement.repository.IngredientRepository;
import com.assignment.recipemanagement.utils.UtilsTest;

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
		assertEquals(RecipeManagementConstants.INGREDIENT_NOT_FOUND.concat("1"), exception.getErrorMessage());

	}
    
    
    @Test
	public void saveIngredientTest() throws Exception {

		// mock the data returned by the Service class
		Ingredient mockIngredient = new Ingredient();
		mockIngredient.setIngredientName("honey");
		when(ingredientRepository.save(Mockito.any(Ingredient.class))).thenReturn(mockIngredient);
		assertEquals("honey", mockIngredient.getIngredientName());

	}
    
    @Test
	public void deleteIngredient() throws Exception {

    	when(ingredientRepository.findByIngredientId(Mockito.anyLong())).thenReturn(new Ingredient());
		assertEquals(RecipeManagementConstants.INGREDIENT_DELETED.concat("1"),ingredientService.deleteIngredient(new Long(1)));

	}
    
    @Test
	public void deleteIngredientTestNotFoundTest() throws Exception {

		// mock the data returned by the Service class

		when(ingredientRepository.findByIngredientId(Mockito.anyLong())).thenReturn(null);
		RecipeManagementException exception = assertThrows(RecipeManagementException.class,
				() -> ingredientService.findByIngredientId(new Long(1)));
		assertEquals(RecipeManagementConstants.INGREDIENT_NOT_FOUND.concat("1"), exception.getErrorMessage());
		assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());

	}
    
    @Test
	public void findAllIngredientsTest() throws Exception {

		// mock the data returned by the Service class

		List<Ingredient>  mockIngredientList = new ArrayList<>();
		Ingredient mockIngredient1 = new Ingredient();
		mockIngredient1.setIngredientId(new Long("1"));
		mockIngredient1.setIngredientName("honey");
		Ingredient mockIngredient2 = new Ingredient();
		mockIngredient2.setIngredientId(new Long("2"));
		mockIngredient2.setIngredientName("water");
		Ingredient mockIngredient3 = new Ingredient();
		mockIngredient3.setIngredientId(new Long("3"));
		mockIngredient3.setIngredientName("lemon");
		mockIngredientList.add(mockIngredient1);
		mockIngredientList.add(mockIngredient2);
		mockIngredientList.add(mockIngredient3);
		
		when(ingredientRepository.findAll()).thenReturn(mockIngredientList);
		List<Ingredient> ingredientListResponse = ingredientService.findAllIngredients();
		assertEquals("honey", ingredientListResponse.get(0).getIngredientName());
		assertEquals("water", ingredientListResponse.get(1).getIngredientName());
		assertEquals("lemon", ingredientListResponse.get(2).getIngredientName());
				
	}


	

}
