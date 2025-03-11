package com.api.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.restaurant.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
