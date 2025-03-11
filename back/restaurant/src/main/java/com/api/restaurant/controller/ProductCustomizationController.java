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

import com.api.restaurant.DTO.ProductCustomizationDTO;
import com.api.restaurant.model.ProductCustomization;
import com.api.restaurant.service.ProductCustomizationService;

@RestController
@RequestMapping("productCustomizations")
public class ProductCustomizationController {
    @Autowired
    ProductCustomizationService productCustomizationService;

    @PostMapping
    public ResponseEntity<ProductCustomization> create(@RequestBody ProductCustomizationDTO productCustomizationDTO) {
        ProductCustomization createdProductCustomization = productCustomizationService.create(productCustomizationDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                      .body(createdProductCustomization);
    }

    @PutMapping
    public ResponseEntity<ProductCustomization> update(@RequestBody ProductCustomizationDTO productCustomizationDTO) {
        ProductCustomization updatedProductCustomization = productCustomizationService.update(productCustomizationDTO);
        return ResponseEntity.ok(updatedProductCustomization);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productCustomizationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
