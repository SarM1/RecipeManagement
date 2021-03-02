package com.assignment.recipemanagement.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assignment.recipemanagement.Utils.RecipeManagementConstants;
import com.assignment.recipemanagement.entity.Ingredient;
import com.assignment.recipemanagement.entity.Recipe;
import com.assignment.recipemanagement.entity.RecipeDetail;
import com.assignment.recipemanagement.exception.RecipeManagementException;
import com.assignment.recipemanagement.service.IngredientService;
import com.assignment.recipemanagement.service.RecipeService;
//import com.assignment.recipemanagement.service.UserService;
import com.assignment.recipemanagement.utils.UtilsTest;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public class RecipeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private RecipeService recipeService;

	//@MockBean
	//private UserService userService;

	@MockBean
	private IngredientService ingredientService;

	@Test
	public void findByRecipeIdTest() throws Exception {

		// mock the data returned by the Service class

		String uri = "/recipes/1";

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

		when(recipeService.findByRecipeId(Mockito.anyLong())).thenReturn(mockRecipe1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri)
				.accept(org.springframework.http.MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.print("mvc Result" + mvcResult.getResponse());

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		Recipe recipe = mapJsontoObject(content, Recipe.class);
		assertEquals("lemon juice", recipe.getRecipeName());
		assertEquals(true, recipe.getIsVeg());
		assertEquals(new Integer(2), recipe.getNoOfPeople());

	}

	@Test
	public void findAllRecipesTest() throws Exception {

		// mock the data returned by the Service class

		String uri = "/recipes/all";

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

		when(recipeService.findAllRecipes()).thenReturn(mockRecipeList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri)
				.accept(org.springframework.http.MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.print("mvc Result" + mvcResult.getResponse());

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();

		Recipe[] recipeArray = mapJsontoObject(content, Recipe[].class);
		List<Recipe> recipeListResponse = Arrays.asList(recipeArray);
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

		String uri = "/recipes/";

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

		when(recipeService.saveRecipe(Mockito.any(Recipe.class))).thenReturn(mockRecipe);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
				.content(UtilsTest.mapObjectToJson(mockRecipe));

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.print("mvc Result" + mvcResult.getResponse());

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		Recipe recipe = mapJsontoObject(content, Recipe.class);
		assertEquals("lemon juice", recipe.getRecipeName());
		assertEquals(true, recipe.getIsVeg());
		assertEquals(new Integer(2), recipe.getNoOfPeople());
		assertEquals("mix all the three ingredients.Tasty and healthy lemon juice is ready", recipe.getProcedure());

	}

	@Test
	public void saveAndUpdateRecipeTest() throws Exception {

		// mock the data returned by the Service class

		String uri = "/recipes/";

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

		when(recipeService.saveRecipe(Mockito.any(Recipe.class))).thenReturn(mockRecipe);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(uri)
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
				.content(UtilsTest.mapObjectToJson(mockRecipe));

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.print("mvc Result" + mvcResult.getResponse());

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		Recipe recipe = mapJsontoObject(content, Recipe.class);
		assertEquals("lemon juice", recipe.getRecipeName());
		assertEquals(true, recipe.getIsVeg());
		assertEquals(new Integer(2), recipe.getNoOfPeople());
		assertEquals("mix all the three ingredients.Tasty and healthy lemon juice is ready", recipe.getProcedure());

	}

	@Test
	public void deleteRecipeTest() throws Exception {

		// mock the data returned by the Service class

		when(recipeService.deleteRecipe(Mockito.anyLong())).thenReturn(RecipeManagementConstants.RECIPE_DELETED.concat("1"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/recipes/{id}", 1);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		// Ingredient ingredient = UtilsTest.mapJsontoObject(content, Ingredient.class);
		assertEquals(RecipeManagementConstants.RECIPE_DELETED.concat("1"), content);

	}

	@Test
	public void deleteRecipeandRecipedoesNotExist() throws Exception {

		when(recipeService.deleteRecipe(Mockito.anyLong()))
				.thenThrow(new RecipeManagementException(RecipeManagementConstants.RECIPE_NOT_FOUND.concat("1"), HttpStatus.NOT_FOUND));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/recipes/{id}", 1);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(mvcResult.getResolvedException() instanceof RecipeManagementException);

		RecipeManagementException recipeManagementException = (RecipeManagementException) mvcResult
				.getResolvedException();
		assertEquals(RecipeManagementConstants.RECIPE_NOT_FOUND.concat("1"), recipeManagementException.getErrorMessage());

	}

	protected <T> T mapJsontoObject(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);

	}
}
