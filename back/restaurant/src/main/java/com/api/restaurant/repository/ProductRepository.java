package com.api.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.restaurant.model.Product;

public interface ProductRepository extends  JpaRepository<Product, Long>{
    @Query("SELECT p FROM Product p " + 
    "LEFT JOIN FETCH p.customizations c " + 
    "LEFT JOIN FETCH c.customizationValues cv " + 
    "WHERE p.id = :productId")
    public Optional<Product> getProductWithCustomizations(Long productId);

    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.id = :categoryId")
    public List<Product> getProductByCategory(Long categoryId);
}
