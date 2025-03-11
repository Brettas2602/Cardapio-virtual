package com.api.restaurant.controller;

import java.util.List;

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

import com.api.restaurant.DTO.ProductRequestDTO;
import com.api.restaurant.model.Product;
import com.api.restaurant.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}/customizations")
    public ResponseEntity<Product> getProductWithCustomizationsById(@PathVariable Long id) {
        Product product = productService.getProductWithCustomizationsById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getAllProductsByCategoryId(@PathVariable Long id) {
        List<Product> products = productService.getAllProductsByCategoryId(id);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductRequestDTO productRequestDTO) {
        Product createdProduct = productService.create(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(createdProduct);
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody ProductRequestDTO productRequestDTO) {
        Product updatedProduct = productService.update(productRequestDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build(); 
    }
}
