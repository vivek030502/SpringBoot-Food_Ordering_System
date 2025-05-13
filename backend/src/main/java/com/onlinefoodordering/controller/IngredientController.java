package com.onlinefoodordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinefoodordering.model.IngredientCategory;
import com.onlinefoodordering.model.IngredientsItem;
import com.onlinefoodordering.request.IngredientCategoryRequest;
import com.onlinefoodordering.request.IngredientRequest;
import com.onlinefoodordering.service.IngredientsService;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientsService ingredientsService;
    
    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest req) throws Exception {

        // Call service to create ingredient category
        IngredientCategory item = ingredientsService.createIngredientCategory(req.getName(), req.getRestaurantId());

        // Return the created ingredient category with HTTP status 201 (Created)
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }
    
    @PostMapping
    public ResponseEntity<IngredientsItem> createIngredientItem(
            @RequestBody IngredientRequest req) throws Exception {

        // Create ingredient item using service method
        IngredientsItem item = ingredientsService.createIngredientItem(
                req.getRestaurantId(), req.getName(), req.getCategoryId());

        // Return the created ingredient item with HTTP status 201 (Created)
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientsItem> updateIngredientStock(
            @PathVariable Long id) throws Exception {

        // Update the ingredient stock using service
        IngredientsItem item = ingredientsService.updateStock(id);

        // Return the updated ingredient item with HTTP status 200 (OK)
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredients(
            @PathVariable Long id) throws Exception {

        // Fetch all ingredients associated with the restaurant
        List<IngredientsItem> items = ingredientsService.findRestaurantsIngredients(id);

        // Return the list of ingredients with HTTP status 200 (OK)
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
            @PathVariable Long id) throws Exception {

        // Fetch ingredient categories for the given restaurant
        List<IngredientCategory> items = ingredientsService.findIngredientCategoryByRestaurantId(id);

        // Return the list of categories with HTTP status 200 (OK)
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}
