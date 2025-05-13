package com.onlinefoodordering.request;

import java.util.List;

import com.onlinefoodordering.model.Category;
import com.onlinefoodordering.model.IngredientsItem;

import lombok.Data;

@Data
public class CreateFoodRequest {
	
	private String name;

	private String description;

	private Long price;

	private Category category;

	private List<String> images;

	private Long restaurantId;

	private boolean vegetarian;

	private boolean seasonal;

	private List<IngredientsItem> ingredients;

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

	public Long getPrice() {
		// TODO Auto-generated method stub
		return price;
	}

	public List<IngredientsItem> getIngredients() {
	    return ingredients;
	}


	public boolean isSeasonal() {
	    return seasonal;
	}


	public Long getRestaurantId() {
		// TODO Auto-generated method stub
		return restaurantId;
	}

	public Category getCategory() {
		// TODO Auto-generated method stub
		return category;
	}

}