package com.api.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.restaurant.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c " +
            "LEFT JOIN FETCH c.cartItems ci " +
            "LEFT JOIN FETCH ci.cartItemCustomizations cic " +
            "WHERE c.user.id = :userId")
    public Optional<Cart> getCartWithCustomizationsByUser(Long userId);

    @Query("SELECT c FROM Cart c LEFT JOIN FETCH c.cartItems ci LEFT JOIN FETCH ci.cartItemCustomizations WHERE c.id = :id")
    public Optional<Cart> getCartWithCustomizations(@Param("id") Long id);

}
