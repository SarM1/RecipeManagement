package com.assignment.recipemanagement.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.assignment.recipemanagement.entity.Ingredient;
import com.assignment.recipemanagement.exception.RecipeManagementException;
import com.assignment.recipemanagement.service.IngredientService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Sarika Mhetre
 *
 */
@RestController
@RequestMapping("/ingredients")
@Slf4j
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;

	/**
	 * 
	 * @param ingredient
	 * @return
	 */
	@GetMapping(value = "/login")
	public ResponseEntity<String> login() {
		
		return ResponseEntity.ok(RecipeManagementConstants.WELCOME_MESSAGE);
	}

	/**
	 * save an ingredient
	 * 
	 * @param ingredient
	 * @return saved Ingredient
	 */
	@PostMapping("/")
	public ResponseEntity<Ingredient> saveIngredient(@Valid @RequestBody Ingredient ingredient) {
		
		return new ResponseEntity<>(ingredientService.saveIngredient(ingredient), HttpStatus.OK);
	}

	/**
	 * save or update an ingredient
	 * 
	 * @param ingredient
	 * @return update Ingredient
	 */
	@PutMapping("/")
	public ResponseEntity<Ingredient> saveorUpdateIngredient(@Valid @RequestBody Ingredient ingredient) {
		
		return new ResponseEntity<>(ingredientService.saveIngredient(ingredient), HttpStatus.OK);
	}

	/**
	 * Delete an ingredient
	 * 
	 * @param ingredientId
	 * @return String message if the Ingredient is deleted successfully
	 * @throws RecipeManagementException
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteIngredient(@PathVariable("id") Long ingredientId)
			throws RecipeManagementException {
		
		return new ResponseEntity<>(ingredientService.deleteIngredient(ingredientId), HttpStatus.OK);

	}

	/**
	 * find an ingredient by its id
	 * 
	 * @param ingredientId
	 * @return Ingredient by the id
	 * @throws RecipeManagementException
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Ingredient> findByIngredientId(@PathVariable("id") Long ingredientId)
			throws RecipeManagementException {
		
		return new ResponseEntity<>(ingredientService.findByIngredientId(ingredientId), HttpStatus.OK);

	}

	/**
	 * find all the ingredients
	 * 
	 * @return List of Ingredient
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Ingredient>> findAllIngredients() {
		
		return new ResponseEntity<>(ingredientService.findAllIngredients(), HttpStatus.OK);

	}

}
