package com.assignment.recipemanagement.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Ingredient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1167203654934810640L;

	public Ingredient(Long ingredientId, String ingredientName) {
		super();
		this.ingredientId = ingredientId;
		this.ingredientName = ingredientName;
	}

	public Ingredient() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long ingredientId;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "MM-dd-yyyy HH:mm")
	@JsonProperty("date")
	private LocalDateTime localDateTime;

	@Size(min = 3, message = "ingredientName size  cannnot be less than 3 characters")
	@NotNull
	private String ingredientName;

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public Long getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Long ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;

	}

}
