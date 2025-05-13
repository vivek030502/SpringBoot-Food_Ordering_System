package com.onlinefoodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodordering.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
	public Cart findByCustomerId(Long userId);

}
