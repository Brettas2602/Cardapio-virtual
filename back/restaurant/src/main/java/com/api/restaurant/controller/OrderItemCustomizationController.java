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

import com.api.restaurant.DTO.OrderItemCustomizationDTO;
import com.api.restaurant.model.OrderItemCustomization;
import com.api.restaurant.service.OrderItemCustomizationService;

@RestController
@RequestMapping("orderItemCustomizations")
public class OrderItemCustomizationController {
    @Autowired
    OrderItemCustomizationService orderItemCustomizationService;

    @PostMapping
    public ResponseEntity<OrderItemCustomization> create(@RequestBody OrderItemCustomizationDTO orderItemCustomizationDTO) {
        OrderItemCustomization createdOrderItemCustomization = orderItemCustomizationService.create(orderItemCustomizationDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(createdOrderItemCustomization);
    }

    @PutMapping
    public ResponseEntity<OrderItemCustomization> update(@RequestBody OrderItemCustomizationDTO orderItemCustomizationDTO) {
        OrderItemCustomization updatedOrderItemCustomization = orderItemCustomizationService.update(orderItemCustomizationDTO);
        return ResponseEntity.ok(updatedOrderItemCustomization);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderItemCustomizationService.delete(id);
        return ResponseEntity.noContent().build(); 
    }
}
