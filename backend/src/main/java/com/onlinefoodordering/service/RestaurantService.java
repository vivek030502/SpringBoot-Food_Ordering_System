package com.onlinefoodordering.service;

import java.util.List;

import com.onlinefoodordering.dto.RestaurantDto;
import com.onlinefoodordering.model.Restaurant;
import com.onlinefoodordering.model.User;
import com.onlinefoodordering.request.CreateRestaurantRequest;

public interface RestaurantService {
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;

	public void deleteRestaurant(Long restaurantId) throws Exception;

	public List<Restaurant> getAllRestaurant();

	public List<Restaurant> searchRestaurant(String keyword);

	public Restaurant findRestaurantById(Long id) throws Exception;

	public Restaurant getRestaurantByUserId(Long userId) throws Exception;

	public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;

	public Restaurant updateRestaurantStatus(Long id) throws Exception;

}

