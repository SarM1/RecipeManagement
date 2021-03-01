package com.assignment.recipemanagement.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assignment.recipemanagement.entity.Ingredient;
import com.assignment.recipemanagement.exception.RecipeManagementException;
import com.assignment.recipemanagement.service.IngredientService;
import com.assignment.recipemanagement.service.RecipeService;
import com.assignment.recipemanagement.utils.UtilsTest;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public class IngredientControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private IngredientService ingredientService;

	//@MockBean
	//private UserService userService;

	@MockBean
	private RecipeService recipeService;

	@Test
	public void findByIngredientIdTest() throws Exception {

		// mock the data returned by the Service class

		String uri = "/ingredients/1";

		Ingredient mockIngredient = new Ingredient();
		mockIngredient.setIngredientName("honey");
		mockIngredient.setIngredientId(new Long(1));

		when(ingredientService.findByIngredientId(Mockito.anyLong())).thenReturn(mockIngredient);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri)
				.accept(org.springframework.http.MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		Ingredient ingredient = UtilsTest.mapJsontoObject(content, Ingredient.class);
		assertEquals("honey", ingredient.getIngredientName());

	}

	@Test
	public void findByIngredientIdandIngredientNotFoundTest() throws Exception {

		// mock the data returned by the Service class

		String uri = "/ingredients/1";

		when(ingredientService.findByIngredientId(Mockito.anyLong())).thenThrow(
				new RecipeManagementException("Ingredient not found for IngredientId 1", HttpStatus.NOT_FOUND));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri)
				.accept(org.springframework.http.MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		assertTrue(mvcResult.getResolvedException() instanceof RecipeManagementException);

		RecipeManagementException recipeManagementException = (RecipeManagementException) mvcResult
				.getResolvedException();
		assertEquals("Ingredient not found for IngredientId 1", recipeManagementException.getErrorMessage());

	}

	@Test
	public void saveIngredientTest() throws Exception {

		// mock the data returned by the Service class

		String uri = "/ingredients/";

		Ingredient mockIngredient = new Ingredient();
		mockIngredient.setIngredientName("honey");
		// mockIngredient.setIngredientId(new Long(1));
		when(ingredientService.saveIngredient(Mockito.any(Ingredient.class))).thenReturn(mockIngredient);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.content(UtilsTest.mapObjectToJson(mockIngredient));

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.print("mvc Result" + mvcResult.getResponse());

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		Ingredient ingredient = UtilsTest.mapJsontoObject(content, Ingredient.class);
		assertEquals("honey", ingredient.getIngredientName());

	}

	@Test
	public void saveOrUpdateIngredientTest() throws Exception {

		String uri = "/ingredients/";

		Ingredient mockIngredient = new Ingredient();
		mockIngredient.setIngredientName("honey");
		// mockIngredient.setIngredientId(new Long(1));
		when(ingredientService.saveIngredient(Mockito.any(Ingredient.class))).thenReturn(mockIngredient);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON)
				.content(UtilsTest.mapObjectToJson(mockIngredient));

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.print("mvc Result" + mvcResult.getResponse());

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		Ingredient ingredient = UtilsTest.mapJsontoObject(content, Ingredient.class);
		assertEquals("honey", ingredient.getIngredientName());

	}

	@Test
	public void deleteIngredient() throws Exception {

		when(ingredientService.deleteIngredient(Mockito.anyLong()))
				.thenReturn("Ingredient is deleted for ingredientId 1");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/ingredients/{id}", 1);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		// Ingredient ingredient = UtilsTest.mapJsontoObject(content, Ingredient.class);
		assertEquals("Ingredient is deleted for ingredientId 1", content);

	}

	@Test
	public void deleteIngredientandIngredientdoesNotExist() throws Exception {

		when(ingredientService.deleteIngredient(Mockito.anyLong())).thenThrow(
				new RecipeManagementException("Ingredient not found for IngredientId 1", HttpStatus.NOT_FOUND));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/ingredients/{id}", 1);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(mvcResult.getResolvedException() instanceof RecipeManagementException);

		RecipeManagementException recipeManagementException = (RecipeManagementException) mvcResult
				.getResolvedException();
		assertEquals("Ingredient not found for IngredientId 1", recipeManagementException.getErrorMessage());

	}

	@Test
	public void getAllIngredients() throws Exception {

		String uri = "/ingredients/all";
		Ingredient mockIngredient1 = new Ingredient();
		mockIngredient1.setIngredientId(new Long("1"));
		mockIngredient1.setIngredientName("honey");

		Ingredient mockIngredient2 = new Ingredient();
		mockIngredient2.setIngredientId(new Long("2"));
		mockIngredient2.setIngredientName("tea powder");

		List<Ingredient> mockIngredientList = new ArrayList<Ingredient>();
		mockIngredientList.add(mockIngredient1);
		mockIngredientList.add(mockIngredient2);

		when(ingredientService.findAllIngredients()).thenReturn(mockIngredientList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		Ingredient[] ingredientArray = UtilsTest.mapJsontoObject(content, Ingredient[].class);
		assertTrue(ingredientArray.length > 0);

	}

}
