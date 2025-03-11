package com.api.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.restaurant.DTO.CartItemDTO;
import com.api.restaurant.model.CartItem;
import com.api.restaurant.service.CartItemService;

@RestController
@RequestMapping("cartItems")
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<CartItem> create(@RequestBody CartItemDTO cartItemDTO) {
        CartItem createdCartItem = cartItemService.create(cartItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(createdCartItem);
    }

    @PutMapping
    public ResponseEntity<CartItem> update(@RequestBody CartItemDTO cartItemDTO) {
        CartItem updatedCartItem = cartItemService.update(cartItemDTO);
        return ResponseEntity.ok(updatedCartItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartItemService.delete(id);
        return ResponseEntity.noContent().build(); 
    }
}
