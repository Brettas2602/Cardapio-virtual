package com.api.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurant.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    
}
