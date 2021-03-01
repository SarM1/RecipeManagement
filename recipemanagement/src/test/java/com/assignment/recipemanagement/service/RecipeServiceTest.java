package com.assignment.recipemanagement.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.assignment.recipemanagement.entity.Ingredient;
import com.assignment.recipemanagement.entity.Recipe;
import com.assignment.recipemanagement.entity.RecipeDetail;
import com.assignment.recipemanagement.exception.RecipeManagementException;
import com.assignment.recipemanagement.repository.RecipeRepository;

//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RecipeServiceTest {

	@Mock
	RecipeRepository recipeRepository;

	@InjectMocks
	RecipeService recipeService;

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void findByRecipeIdWhenRecipeIdisNotFound() {

		when(recipeRepository.findByRecipeId(Mockito.anyLong())).thenReturn(null);
		RecipeManagementException exception = assertThrows(RecipeManagementException.class,
				() -> recipeService.findByRecipeId(new Long(1)));
		assertEquals("Recipe not found for RecipeId 1", exception.getErrorMessage());

	}

	@Test
	public void findByRecipeIdTestWhenRecipeIdIsFound() throws RecipeManagementException {
		Recipe mockRecipe1 = new Recipe();

		Ingredient mockIngredient1 = new Ingredient();
		mockIngredient1.setIngredientId(new Long("1"));
		mockIngredient1.setIngredientName("honey");
		Ingredient mockIngredient2 = new Ingredient();
		mockIngredient2.setIngredientId(new Long("2"));
		mockIngredient2.setIngredientName("water");
		Ingredient mockIngredient3 = new Ingredient();
		mockIngredient2.setIngredientId(new Long("3"));
		mockIngredient2.setIngredientName("lemon");

		RecipeDetail mockRecipeDetail1 = new RecipeDetail();
		mockRecipeDetail1.setQuantity("1 teaspoon");
		mockRecipeDetail1.setIngredient(mockIngredient1);

		RecipeDetail mockRecipeDetail2 = new RecipeDetail();
		mockRecipeDetail2.setQuantity("2 cups");
		mockRecipeDetail2.setIngredient(mockIngredient1);

		RecipeDetail mockRecipeDetail3 = new RecipeDetail();
		mockRecipeDetail2.setQuantity("1 cup");
		mockRecipeDetail2.setIngredient(mockIngredient1);

		mockRecipeDetail3.setQuantity("1");
		mockRecipeDetail3.setIngredient(mockIngredient3);

		List<RecipeDetail> recipeDetailList = new ArrayList<RecipeDetail>();
		recipeDetailList.add(mockRecipeDetail1);
		recipeDetailList.add(mockRecipeDetail2);
		recipeDetailList.add(mockRecipeDetail3);

		mockRecipe1.setRecipeId(new Long(1));

		mockRecipe1.setRecipeName("lemon juice");
		mockRecipe1.setIsVeg(true);
		mockRecipe1.setNoOfPeople(2);
		mockRecipe1.setRecipeDetails(recipeDetailList);

		when(recipeRepository.findByRecipeId(Mockito.anyLong())).thenReturn(mockRecipe1);
		Recipe responseRecipe = recipeService.findByRecipeId(new Long(1));
		assertThat(responseRecipe).isNotNull();
		assertEquals("lemon juice", responseRecipe.getRecipeName());

	}

	@Test
	public void findAllRecipesTest() throws Exception {

		// mock the data returned by the Service class

		Recipe mockRecipe1 = new Recipe();
		Ingredient mockIngredient1 = new Ingredient();
		mockIngredient1.setIngredientId(new Long("1"));
		mockIngredient1.setIngredientName("honey");
		Ingredient mockIngredient2 = new Ingredient();
		mockIngredient2.setIngredientId(new Long("2"));
		mockIngredient2.setIngredientName("water");
		Ingredient mockIngredient3 = new Ingredient();
		mockIngredient3.setIngredientId(new Long("3"));
		mockIngredient3.setIngredientName("lemon");

		RecipeDetail mockRecipeDetail1 = new RecipeDetail();
		mockRecipeDetail1.setQuantity("1 teaspoon");
		mockRecipeDetail1.setIngredient(mockIngredient1);

		RecipeDetail mockRecipeDetail2 = new RecipeDetail();
		mockRecipeDetail2.setQuantity("2 cups");
		mockRecipeDetail2.setIngredient(mockIngredient2);

		RecipeDetail mockRecipeDetail3 = new RecipeDetail();
		mockRecipeDetail3.setQuantity("1");
		mockRecipeDetail3.setIngredient(mockIngredient3);

		List<RecipeDetail> recipeDetailList1 = new ArrayList<RecipeDetail>();
		recipeDetailList1.add(mockRecipeDetail1);
		recipeDetailList1.add(mockRecipeDetail2);
		recipeDetailList1.add(mockRecipeDetail3);
		mockRecipe1.setRecipeId(new Long(1));
		mockRecipe1.setRecipeName("lemon juice");
		mockRecipe1.setIsVeg(true);
		mockRecipe1.setNoOfPeople(2);
		mockRecipe1.setProcedure("mix all the three ingredients.Tasty and healthy lemon juice is ready");
		mockRecipe1.setRecipeDetails(recipeDetailList1);

		Recipe mockRecipe2 = new Recipe();
		// ingredients for second recipe
		Ingredient mockIngredient4 = new Ingredient();
		mockIngredient1.setIngredientId(new Long("1"));
		mockIngredient1.setIngredientName("maggi noodles");

		Ingredient mockIngredient5 = new Ingredient();
		mockIngredient2.setIngredientId(new Long("2"));
		mockIngredient2.setIngredientName("water");

		// recipe details for second recipe

		RecipeDetail mockRecipeDetail4 = new RecipeDetail();
		mockRecipeDetail1.setQuantity("1 packet");
		mockRecipeDetail1.setIngredient(mockIngredient4);

		RecipeDetail mockRecipeDetail5 = new RecipeDetail();
		mockRecipeDetail2.setQuantity("2 cups");
		mockRecipeDetail2.setIngredient(mockIngredient5);

		List<RecipeDetail> recipeDetailList2 = new ArrayList<RecipeDetail>();
		recipeDetailList2.add(mockRecipeDetail4);
		recipeDetailList2.add(mockRecipeDetail5);

		mockRecipe2.setRecipeId(new Long(2));

		mockRecipe2.setRecipeName("maggi noodles");
		mockRecipe2.setIsVeg(true);
		mockRecipe2.setNoOfPeople(1);
		mockRecipe2.setProcedure("boil water ,add noodles and masala.cook for 3 minutes .Maggi noodles is ready");
		mockRecipe2.setRecipeDetails(recipeDetailList2);

		List<Recipe> mockRecipeList = new ArrayList<Recipe>();
		mockRecipeList.add(mockRecipe1);
		mockRecipeList.add(mockRecipe2);

		when(recipeRepository.findAll()).thenReturn(mockRecipeList);
		List<Recipe> recipeListResponse = recipeService.findAllRecipes();
		assertEquals("lemon juice", recipeListResponse.get(0).getRecipeName());
		assertEquals(true, recipeListResponse.get(0).getIsVeg());
		assertEquals("mix all the three ingredients.Tasty and healthy lemon juice is ready",
				mockRecipeList.get(0).getProcedure());
		assertEquals("maggi noodles", recipeListResponse.get(1).getRecipeName());
		assertEquals(true, recipeListResponse.get(1).getIsVeg());
		assertEquals("boil water ,add noodles and masala.cook for 3 minutes .Maggi noodles is ready",
				mockRecipeList.get(1).getProcedure());
	}

	@Test
	public void saveRecipeTest() throws Exception {

		// mock the data returned by the Service class

		Recipe mockRecipe = new Recipe();

		Ingredient mockIngredient1 = new Ingredient();
		mockIngredient1.setIngredientId(new Long("1"));
		mockIngredient1.setIngredientName("honey");
		Ingredient mockIngredient2 = new Ingredient();
		mockIngredient2.setIngredientId(new Long("2"));
		mockIngredient2.setIngredientName("water");
		Ingredient mockIngredient3 = new Ingredient();
		mockIngredient2.setIngredientId(new Long("3"));
		mockIngredient2.setIngredientName("lemon");

		RecipeDetail mockRecipeDetail1 = new RecipeDetail();
		mockRecipeDetail1.setQuantity("1 teaspoon");
		mockRecipeDetail1.setIngredient(mockIngredient1);

		RecipeDetail mockRecipeDetail2 = new RecipeDetail();
		mockRecipeDetail2.setQuantity("2 cups");
		mockRecipeDetail2.setIngredient(mockIngredient1);

		RecipeDetail mockRecipeDetail3 = new RecipeDetail();
		mockRecipeDetail2.setQuantity("1 cup");
		mockRecipeDetail2.setIngredient(mockIngredient1);

		mockRecipeDetail3.setQuantity("1");
		mockRecipeDetail3.setIngredient(mockIngredient3);

		List<RecipeDetail> recipeDetailList = new ArrayList<RecipeDetail>();
		recipeDetailList.add(mockRecipeDetail1);
		recipeDetailList.add(mockRecipeDetail2);
		recipeDetailList.add(mockRecipeDetail3);

		mockRecipe.setRecipeId(new Long(1));

		mockRecipe.setRecipeName("lemon juice");
		mockRecipe.setIsVeg(true);
		mockRecipe.setNoOfPeople(2);
		mockRecipe.setProcedure("mix all the three ingredients.Tasty and healthy lemon juice is ready");
		mockRecipe.setRecipeDetails(recipeDetailList);

		when(recipeRepository.save(Mockito.any(Recipe.class))).thenReturn(mockRecipe);
		Recipe recipe = recipeService.saveRecipe(mockRecipe);
		assertEquals("lemon juice", recipe.getRecipeName());
		assertEquals(true, recipe.getIsVeg());
		assertEquals(new Integer(2), recipe.getNoOfPeople());
		assertEquals("mix all the three ingredients.Tasty and healthy lemon juice is ready", recipe.getProcedure());

	}

}
