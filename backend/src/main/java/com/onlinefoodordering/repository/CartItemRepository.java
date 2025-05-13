package com.onlinefoodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodordering.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	
	

}
