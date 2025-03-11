package com.api.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.restaurant.DTO.CartDTO;
import com.api.restaurant.model.Cart;
import com.api.restaurant.service.CartService;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartWithCustomizationsByUserId(@PathVariable Long id) {
        Cart cart = cartService.getCartWithCustomizationsByUserId(id);
        return ResponseEntity.ok(cart);
    }

    @PostMapping
    public ResponseEntity<Cart> create(@RequestBody CartDTO cartDTO) {
        Cart createdCart = cartService.create(cartDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(createdCart);
    }

    @PutMapping
    public ResponseEntity<Cart> update(@RequestBody CartDTO cartDTO) {
        Cart updatedCart = cartService.update(cartDTO);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartService.delete(id);
        return ResponseEntity.noContent().build(); 
    }
}
