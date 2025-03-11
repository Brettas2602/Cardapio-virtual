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

import com.api.restaurant.DTO.OrderItemDTO;
import com.api.restaurant.model.OrderItem;
import com.api.restaurant.service.OrderItemService;

@RestController
@RequestMapping("orderItems")
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderItem> create(@RequestBody OrderItemDTO orderItemDTO) {
        OrderItem createdOrderItem = orderItemService.create(orderItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(createdOrderItem);
    }

    @PutMapping
    public ResponseEntity<OrderItem> update(@RequestBody OrderItemDTO orderItemDTO) {
        OrderItem updatedOrderItem = orderItemService.update(orderItemDTO);
        return ResponseEntity.ok(updatedOrderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderItemService.delete(id);
        return ResponseEntity.noContent().build(); 
    }
}
