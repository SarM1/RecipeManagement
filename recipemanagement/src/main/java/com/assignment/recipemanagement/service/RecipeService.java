package com.assignment.recipemanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.assignment.recipemanagement.Utils.RecipeManagementConstants;
import com.assignment.recipemanagement.entity.Recipe;
import com.assignment.recipemanagement.exception.RecipeManagementException;
import com.assignment.recipemanagement.repository.RecipeRepository;

@Service
public class RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	/**
	 * saves the Recipe to db
	 * @param recipe
	 * @return saved Recipe
	 */
	@Transactional(rollbackOn = Exception.class)
	public Recipe saveRecipe(Recipe recipe) {
		
		recipe.setCreatedOn(LocalDateTime.now());
		return recipeRepository.save(recipe);

	}

	/**
	 * finds recipe with the id 
	 * @param recipeId
	 * @return Recipe found with the given id 
	 * @throws RecipeManagementException
	 */
	public Recipe findByRecipeId(Long recipeId) throws RecipeManagementException {

		Recipe recipe = recipeRepository.findByRecipeId(recipeId);

		if (null != recipe) {

			return recipe;

		} else {
			throw new RecipeManagementException(RecipeManagementConstants.RECIPE_NOT_FOUND+ recipeId, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * deletes the Recipe with the id provided 
	 * @param recipeId
	 * @return String message if the recipe is deleted successfully
	 * @throws RecipeManagementException
	 */
	@Transactional(rollbackOn = Exception.class)
	public String deleteRecipe(Long recipeId) throws RecipeManagementException {

		Recipe recipe = findByRecipeId(recipeId);
		if (null != recipe) {

			recipeRepository.delete(recipe);

			return RecipeManagementConstants.RECIPE_DELETED+ recipeId;
		}

		else {
			throw new RecipeManagementException(RecipeManagementConstants.RECIPE_NOT_FOUND+recipeId, HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * @return list of all the recipes 
	 * 
	 * 
	 */
	public List<Recipe> findAllRecipes() {
		return recipeRepository.findAll();
	}
}
