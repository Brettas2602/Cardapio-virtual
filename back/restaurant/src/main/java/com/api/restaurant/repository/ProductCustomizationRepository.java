package com.api.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurant.model.ProductCustomization;

public interface ProductCustomizationRepository extends JpaRepository<ProductCustomization, Long>{
    
}
