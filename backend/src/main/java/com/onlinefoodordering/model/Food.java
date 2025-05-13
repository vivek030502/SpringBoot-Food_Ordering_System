package com.onlinefoodordering.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

	private String description;

    private Long price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category foodCategory;

    @ElementCollection
    @CollectionTable(name = "food_images", joinColumns = @JoinColumn(name = "food_id"))
    @Column(name = "image_url", length = 1000)
    private List<String> images = new ArrayList<>();

    private boolean isAvailable;

    @ManyToOne
    private Restaurant restaurant;

    private boolean isVegetarian;
    private boolean isSeasonal;

    @ManyToMany
    private List<IngredientsItem> ingredients = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Category getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(Category foodCategory) {
		this.foodCategory = foodCategory;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public boolean isVegetarian() {
		return isVegetarian;
	}

	public void setVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	public boolean isSeasonal() {
		return isSeasonal;
	}

	public void setSeasonal(boolean isSeasonal) {
		this.isSeasonal = isSeasonal;
	}

	public List<IngredientsItem> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientsItem> ingredients) {
		this.ingredients = ingredients;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	

	
}
