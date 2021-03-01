package com.assignment.recipemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.recipemanagement.entity.RecipeDetail;

@Repository
public interface RecipeDetailRepository extends JpaRepository<RecipeDetail, Long> {

}
