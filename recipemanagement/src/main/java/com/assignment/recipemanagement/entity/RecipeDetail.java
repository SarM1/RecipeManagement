package com.assignment.recipemanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)

	private long recipeDetailId;

	@ManyToOne
	@JoinColumn(name = "recipe_recipedetail")
	private Recipe recipe;

	@ManyToOne
	@JoinColumn(name = "recipeDetail_ingredient")
	private Ingredient ingredient;

	private String quantity;

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
