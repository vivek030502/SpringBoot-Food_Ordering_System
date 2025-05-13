package com.onlinefoodordering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodordering.model.IngredientsItem;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long> {
	
	List<IngredientsItem> findByRestaurantId(Long id);

}
