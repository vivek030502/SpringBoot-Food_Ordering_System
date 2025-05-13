package com.onlinefoodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodordering.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
