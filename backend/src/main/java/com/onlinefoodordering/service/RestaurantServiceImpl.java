package com.onlinefoodordering.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodordering.dto.RestaurantDto;
import com.onlinefoodordering.model.Address;
import com.onlinefoodordering.model.Restaurant;
import com.onlinefoodordering.model.User;
import com.onlinefoodordering.repository.AddressRepository;
import com.onlinefoodordering.repository.RestaurantRepository;
import com.onlinefoodordering.repository.UserRepository;
import com.onlinefoodordering.request.CreateRestaurantRequest;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

		Address address = addressRepository.save(req.getAddress());

		Restaurant restaurant = new Restaurant();

		restaurant.setAddress(address);

		restaurant.setContactInformation(req.getContactInformation());

		restaurant.setCuisineType(req.getCuisineType());

		restaurant.setDescription(req.getDescription());

		restaurant.setImages(req.getImages());

		restaurant.setName(req.getName());

		restaurant.setOpeningHours(req.getOpeningHours());

		restaurant.setRegistrationDate(LocalDateTime.now());

		restaurant.setOwner(user);

		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);

		if (restaurant.getCuisineType() != null) {

			restaurant.setCuisineType(updatedRestaurant.getCuisineType());

		}

		if (restaurant.getDescription() != null) {

			restaurant.setDescription(updatedRestaurant.getDescription());

		}

		if (restaurant.getName() != null) {

			restaurant.setName(updatedRestaurant.getName());

		}

		return restaurantRepository.save(restaurant);
	}

	@Override
	public void deleteRestaurant(Long restaurantId) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);

		restaurantRepository.delete(restaurant);

	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		return restaurantRepository.findAll();
	}

	@Override
	public List<Restaurant> searchRestaurant(String keyword) {
		return restaurantRepository.findBySearchQuery(keyword);
	}

	@Override
	public Restaurant findRestaurantById(Long id) throws Exception {
		Optional<Restaurant> opt = restaurantRepository.findById(id);
		if (opt.isEmpty()) {
			throw new Exception("restaurant not found with id" + id);
		}
		return opt.get();
	}

	@Override
	public Restaurant getRestaurantByUserId(Long userId) throws Exception {
	    Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
	    if (restaurant == null) {
	        throw new Exception("Restaurant not found with owner id " + userId);
	    }
	    return restaurant;
	}

	@Override
	public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
		Restaurant restaurant=findRestaurantById(restaurantId);

		RestaurantDto dto=new RestaurantDto();

		dto.setDescription (restaurant.getDescription());

		dto.setImages(restaurant.getImages());

		dto.setTitle(restaurant.getName());

		dto.setId(restaurantId);
		

		boolean isFavorited = false;

		// Get the user's favorite restaurants
		List<RestaurantDto> favorites = user.getFavourites();

		// Check if the restaurant is already in favorites
		for (RestaurantDto favorite : favorites) {
		    if (favorite.getId().equals(restaurantId)) {
		        isFavorited = true;
		        break;
		    }
		}

		// If the restaurant is already favorited, remove it; otherwise, add it to favorites
		if (isFavorited) {
		    favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
		} else {
		    favorites.add(dto);
		}

		
		userRepository.save(user);

		return dto;

	}

	@Override
	public Restaurant updateRestaurantStatus(Long id) throws Exception {
		Restaurant restaurant=findRestaurantById(id);

		restaurant.setOpen(!restaurant.isOpen());

		return restaurantRepository.save(restaurant);
	}

}

