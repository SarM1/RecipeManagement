package com.assignment.recipemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.recipemanagement.Utils.RecipeManagementConstants;
import com.assignment.recipemanagement.entity.Recipe;
import com.assignment.recipemanagement.exception.RecipeManagementException;
import com.assignment.recipemanagement.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/recipes")
@Slf4j
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@GetMapping(value = "/login")
	public ResponseEntity<String> login() {
		return ResponseEntity.ok(RecipeManagementConstants.WELCOME_MESSAGE);
	}

	/**
	 * Save a recipe
	 * @param recipe
	 * @return saved Recipe
	 */
	@PostMapping("/")
	public ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe recipe) {
		
		return new ResponseEntity<>(recipeService.saveRecipe(recipe), HttpStatus.OK);
	}

	/**
	 * Update a recipe
	 * @param recipe
	 * @return saved Recipe
	 */
	@PutMapping("/")
	public ResponseEntity<Recipe> saveorUpdateRecipe(@RequestBody Recipe recipe) {

		return new ResponseEntity<>(recipeService.saveRecipe(recipe), HttpStatus.OK);
	}

	/**
	 * Delete a Recipe
	 * @param recipeId
	 * @return String message if recipe has been deleted successfully
	 * @throws RecipeManagementException
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRecipe(@PathVariable("id") Long recipeId) throws RecipeManagementException {

		return new ResponseEntity<>(recipeService.deleteRecipe(recipeId), HttpStatus.OK);

	}

	/**
	 * find a recipe by its id
	 * @param recipeId
	 * @return Recipe
	 * @throws RecipeManagementException
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Recipe> findByRecipeId(@PathVariable("id") Long recipeId) throws RecipeManagementException {
		
		return new ResponseEntity<>(recipeService.findByRecipeId(recipeId), HttpStatus.OK);

	}

	/**
	 * find all the recipes
	 * @return list of all the recipes
	 * @throws RecipeManagementException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Recipe>> findAllRecipes() throws RecipeManagementException {
		
		return new ResponseEntity<>(recipeService.findAllRecipes(), HttpStatus.OK);

	}

}
