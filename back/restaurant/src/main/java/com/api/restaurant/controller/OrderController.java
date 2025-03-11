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

import com.api.restaurant.DTO.OrderDTO;
import com.api.restaurant.model.Order;
import com.api.restaurant.service.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Order>> getOrderWithCustomizationByUserId(@PathVariable Long id) {
        List<Order> orders = orderService.getOrderWithCustomizationsByUserId(id);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderDTO orderDTO) {
        Order createdOrder = orderService.create(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(createdOrder);
    }

    @PutMapping
    public ResponseEntity<Order> update(@RequestBody OrderDTO orderDTO) {
        Order updatedOrder = orderService.update(orderDTO);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build(); 
    }
}
