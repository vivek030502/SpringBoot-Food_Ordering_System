package com.onlinefoodordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinefoodordering.model.Category;
import com.onlinefoodordering.model.User;
import com.onlinefoodordering.service.CategoryService;
import com.onlinefoodordering.service.UserService;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(
            @RequestBody Category category,
            @RequestHeader("Authorization") String jwt) throws Exception {

        // Find user by JWT token
        User user = userService.findUserByJwtToken(jwt);

        // Create category
        Category createdCategory = categoryService.createCategory(category.getName(), user.getId());

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }
    
    @GetMapping("/category/restaurant/{id}")
    public ResponseEntity<List<Category>> getRestaurantCategory(
    		@PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {

        // Get user from JWT token
        User user = userService.findUserByJwtToken(jwt);

        // Get categories by restaurant ID associated with the user
        List<Category> categories = categoryService.findCategoryByRestaurantId(id);

        // Return response
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
