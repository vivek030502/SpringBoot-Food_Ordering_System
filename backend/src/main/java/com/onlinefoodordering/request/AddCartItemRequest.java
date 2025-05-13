package com.onlinefoodordering.request;

import java.util.List;

import lombok.Data;

@Data
public class AddCartItemRequest {
	
	private long foodId;
	
	private int quantity;
	
	private List<String> ingredients;

	public long getFoodId() {
		return foodId;
	}

	public void setFoodId(long foodId) {
		this.foodId = foodId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}





}
