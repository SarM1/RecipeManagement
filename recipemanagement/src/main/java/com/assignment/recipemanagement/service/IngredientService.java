package com.assignment.recipemanagement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.assignment.recipemanagement.entity.Ingredient;
import com.assignment.recipemanagement.exception.RecipeManagementException;
import com.assignment.recipemanagement.repository.IngredientRepository;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;

	/**
	 * @param ingredient
	 * @return saved Ingredient.
	 * Uses save method of repository
	 */
	public Ingredient saveIngredient(Ingredient ingredient) {

		ingredient.setLocalDateTime(LocalDateTime.now());
		return ingredientRepository.save(ingredient);

	}
	
	

	/**
	 * finds ingredient by the Id of the ingredient
	 * @param ingredientId
	 * @return Ingredient
	 * @throws RecipeManagementException
	 * 
	 */
	public Ingredient findByIngredientId(Long ingredientId) throws RecipeManagementException {

		Ingredient ingredient = ingredientRepository.findByIngredientId(ingredientId);

		if (null != ingredient) {

			return ingredient;

		} else {
			throw new RecipeManagementException("Ingredient not found for IngredientId " + ingredientId,
					HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * get all the Ingredients
	 * @return Ingredient
	 */
	public List<Ingredient> findAllIngredients() {
		return ingredientRepository.findAll();
	}
	

	/**
	 * @param ingredientId
	 * @return String message if the ingredient is deleted successfully
	 * @throws RecipeManagementException
	 */
	public String deleteIngredient(Long ingredientId) throws RecipeManagementException {
		Ingredient ingredient = ingredientRepository.findByIngredientId(ingredientId);

		if (null != ingredient) {

			ingredientRepository.delete(ingredient);
			return "Ingredient is deleted for ingredientId 1";

		} else {
			throw new RecipeManagementException("Ingredient not found for IngredientId " + ingredientId,
					HttpStatus.NOT_FOUND);
		}

	}
}