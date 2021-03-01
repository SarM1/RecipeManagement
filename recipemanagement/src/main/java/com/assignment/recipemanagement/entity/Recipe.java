package com.assignment.recipemanagement.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recipeId;
	
	@Size(min = 3, message = "recipeName size  cannnot be less than 3 characters")
	@NotNull
	private String recipeName;
	
	@Size(min = 3, message = "ingredientName size  cannnot be less than  3 characters")
	@NotNull
	private String procedure;
	private Integer noOfPeople;
	private Boolean isVeg;
	
	@OneToMany(targetEntity = RecipeDetail.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "recipe_recipedetail",referencedColumnName = "recipeId")
	private List<RecipeDetail> recipeDetails;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "MM-dd-yyyy HH:mm")
	@JsonProperty("date")
	private LocalDateTime createdOn;
	
	
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public Long getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	public Integer getNoOfPeople() {
		return noOfPeople;
	}
	public void setNoOfPeople(Integer noOfPeople) {
		this.noOfPeople = noOfPeople;
	}
	
	public Boolean getIsVeg() {
		return isVeg;
	}
	public void setIsVeg(Boolean isVeg) {
		this.isVeg = isVeg;
	}
	public List<RecipeDetail> getRecipeDetails() {
		return recipeDetails;
	}
	public void setRecipeDetails(List<RecipeDetail> recipeDetails) {
		this.recipeDetails = recipeDetails;
	}
	

}
