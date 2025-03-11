package com.api.restaurant.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurant.DTO.OrderDTO;
import com.api.restaurant.exception.ResourceNotFoundException;
import com.api.restaurant.model.Order;
import com.api.restaurant.model.User;
import com.api.restaurant.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public List<Order> findAll() {
        logger.info("Searching all orders");
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        logger.info("Searching for order with ID: " + id);
        return orderRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Order do not exists");
                return new ResourceNotFoundException("Order", id);
            });
    }

    public List<Order> getOrderWithCustomizationsByUserId(Long id) {
        logger.info("Searching for order and customizations with ID: " + id);
        return orderRepository.getOrdersWithCustomizationsByUser(id)
            .orElseThrow(() -> {
                logger.error("Order do not exists");
                return new ResourceNotFoundException("Order", id);
            });
    }

    public Order create(OrderDTO orderDTO) {
        logger.info("Creating order");

        Order order = modelMapper.map(orderDTO, Order.class);

        User user = userService.findById(orderDTO.getUser());
        order.setUser(user);

        return orderRepository.save(order);
    }

    public Order update(OrderDTO orderDTO) {
        logger.info("Checking if order exists");
        Order initialOrder = orderRepository.findById(orderDTO.getId())
            .orElseThrow(() -> {
                logger.error("Order do not exists");
                return new ResourceNotFoundException("Order", orderDTO.getId());
            });

        Order order = modelMapper.map(orderDTO, Order.class);

        User user = userService.findById(orderDTO.getUser());
        order.setUser(user);

        order.setOrderItems(initialOrder.getOrderItems());
        
        logger.info("Updating order");
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        logger.info("Checking if order exists");
        Order orderEntity = orderRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Order do not exists");
                return new ResourceNotFoundException("Order", id);
            });
        logger.info("Deleting order");
        orderRepository.delete(orderEntity);
    }

}
