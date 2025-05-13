package com.onlinefoodordering.service;

import java.util.List;

import com.onlinefoodordering.model.Category;
import com.onlinefoodordering.model.Food;
import com.onlinefoodordering.model.Restaurant;
import com.onlinefoodordering.request.CreateFoodRequest;

public interface FoodService {
	
	public Food createFood(CreateFoodRequest req, Category category, Restaurant restarant) throws Exception;
	
	void deleteFood (Long foodId) throws Exception;


	public List<Food> getRestaurantsFood (Long restaurantId, boolean isVegetarian, boolean isNonveg, boolean isSeasonal, String foodCategory);

	public List<Food> searchFood(String keyword);

	public Food findFoodById(Long foodId) throws Exception;

	public Food updateAvailabilityStatus (Long foodId) throws Exception;

}
