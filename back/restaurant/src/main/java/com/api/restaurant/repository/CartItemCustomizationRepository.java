package com.api.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurant.model.CartItemCustomization;

public interface CartItemCustomizationRepository extends JpaRepository<CartItemCustomization, Long>{
    
}
