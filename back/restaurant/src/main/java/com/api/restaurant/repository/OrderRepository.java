package com.api.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.restaurant.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    @Query("SELECT o FROM Order o " +
    "LEFT JOIN o.orderItems oi " +
    "LEFT JOIN oi.orderItemCustomizations oic " + 
    "WHERE o.user.id = :userId")
    public Optional<List<Order>> getOrdersWithCustomizationsByUser(Long userId);
}
