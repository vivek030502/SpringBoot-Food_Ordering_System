package com.onlinefoodordering.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodordering.model.Category;
import com.onlinefoodordering.model.Food;
import com.onlinefoodordering.model.IngredientCategory;
import com.onlinefoodordering.model.IngredientsItem;
import com.onlinefoodordering.model.Restaurant;
import com.onlinefoodordering.repository.FoodRepository;
import com.onlinefoodordering.repository.IngredientItemRepository;
import com.onlinefoodordering.repository.CategoryRepository;
import com.onlinefoodordering.repository.RestaurantRepository;
import com.onlinefoodordering.request.CreateFoodRequest;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Autowired
//    private RestaurantRepository restaurantRepository;
//    
//    @Autowired
//    private IngredientItemRepository ingredientsItemRepository;


    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
        Food food = new Food();

        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setAvailable(true);
        food.setSeasonal(req.isSeasonal());
        food.setVegetarian(req.isVegetarian());
        food.setCreationDate(new Date());
        food.setIngredients(req.getIngredients());

        Food savedFood = foodRepository.save(food);

		restaurant.getFoods().add(savedFood);

        return savedFood;
    }


    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setRestaurant(null); // Remove the link to the restaurant
        foodRepository.save(food); // Save the food with updated restaurant link
    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonveg, boolean isSeasonal,
            String foodCategory) {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if (isVegetarian) {
            foods = filterByVegetarian(foods, isVegetarian);
        }

        if (isNonveg) {
            foods = filterByNonveg(foods, isNonveg);
        }

        if (isSeasonal) {
            foods = filterBySeasonal(foods, isSeasonal);
        }

        if (foodCategory != null && !foodCategory.equals("")) {
            foods = filterByCategory(foods, foodCategory);
        }

        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        if (foodCategory == null || foodCategory.isEmpty()) {
            return foods; // No category filter applied
        }
        return foods.stream()
            .filter(food -> food.getFoodCategory() != null && food.getFoodCategory().getName().equals(foodCategory))
            .collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
        return foods.stream().filter(food -> !food.isVegetarian()).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream().filter(food -> food.isVegetarian() == isVegetarian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(foodId);
        
        if (optionalFood.isEmpty()) {
        	throw new Exception("Food with ID " + foodId + " not found.");
        }
        return optionalFood.get();
    }
    

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
