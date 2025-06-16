package com.api.restaurant.controller;

import java.util.List;

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

import com.api.restaurant.DTO.CartItemCustomizationDTO;
import com.api.restaurant.model.CartItemCustomization;
import com.api.restaurant.service.CartItemCustomizationService;

@RestController
@RequestMapping("cartItemCustomizations")
public class CartItemCustomizationController {
    @Autowired
    CartItemCustomizationService cartItemCustomizationService;

    @PostMapping
    public ResponseEntity<List<CartItemCustomization>> create(@RequestBody List<CartItemCustomizationDTO> cartItemCustomizationDTO) {
        List<CartItemCustomization> createdCartItemCustomization = cartItemCustomizationService.create(cartItemCustomizationDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                      .body(createdCartItemCustomization);
    }

    @PutMapping
    public ResponseEntity<CartItemCustomization> update(@RequestBody CartItemCustomizationDTO cartItemCustomizationDTO) {
        CartItemCustomization updatedCartItemCustomization = cartItemCustomizationService.update(cartItemCustomizationDTO);
        return ResponseEntity.ok(updatedCartItemCustomization);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartItemCustomizationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
