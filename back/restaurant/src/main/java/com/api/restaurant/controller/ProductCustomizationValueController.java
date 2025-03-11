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

import com.api.restaurant.DTO.ProductCustomizationValueDTO;
import com.api.restaurant.model.ProductCustomizationValue;
import com.api.restaurant.service.ProductCustomizationValueService;

@RestController
@RequestMapping("productCustomizationValues")
public class ProductCustomizationValueController {
    @Autowired
    ProductCustomizationValueService productCustomizationValueService;

    @PostMapping
    public ResponseEntity<ProductCustomizationValue> create(@RequestBody ProductCustomizationValueDTO productCustomizationValueDTO) {
        ProductCustomizationValue createdProductCustomizationValue = productCustomizationValueService.create(productCustomizationValueDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                      .body(createdProductCustomizationValue);
    }

    @PutMapping
    public ResponseEntity<ProductCustomizationValue> update(@RequestBody ProductCustomizationValueDTO productCustomizationValueDTO) {
        ProductCustomizationValue updatedProductCustomizationValue = productCustomizationValueService.update(productCustomizationValueDTO);
        return ResponseEntity.ok(updatedProductCustomizationValue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productCustomizationValueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
