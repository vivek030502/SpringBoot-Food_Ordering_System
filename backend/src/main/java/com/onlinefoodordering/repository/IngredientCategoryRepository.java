package com.onlinefoodordering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodordering.model.IngredientCategory;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long>{
	
	List<IngredientCategory> findByRestaurantId(Long id);

}
