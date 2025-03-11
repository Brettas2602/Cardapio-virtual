package com.api.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurant.model.OrderItemCustomization;

public interface OrderItemCustomizationRepository extends JpaRepository<OrderItemCustomization, Long>{
    
}
