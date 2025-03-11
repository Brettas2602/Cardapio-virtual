package com.api.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurant.model.ProductCustomizationValue;

public interface ProductCustomizationValueRepository extends JpaRepository<ProductCustomizationValue, Long>{
    
}
