package com.api.restaurant.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurant.DTO.OrderItemDTO;
import com.api.restaurant.exception.ResourceNotFoundException;
import com.api.restaurant.model.Order;
import com.api.restaurant.model.OrderItem;
import com.api.restaurant.model.Product;
import com.api.restaurant.repository.OrderItemRepository;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(OrderItemService.class);

    public OrderItem findById(Long id) {
        logger.info("Searching for orderItem with ID: " + id);
        return orderItemRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("OrderItem do not exists");
                    return new ResourceNotFoundException("Order Item", id);
                });
    }

    public List<OrderItem> create(List<OrderItemDTO> orderItemDTOs) {
        logger.info("Creating orderItem");

        List<OrderItem> items = new ArrayList<>();

        for (OrderItemDTO dto : orderItemDTOs) {

            OrderItem orderItem = modelMapper.map(dto, OrderItem.class);

            Order order = orderService.findById(dto.getOrder());
            orderItem.setOrder(order);

            Product product = productService.findById(dto.getProduct());
            orderItem.setProduct(product);

            items.add(orderItem);
        }

        return orderItemRepository.saveAll(items);
    }

    public OrderItem update(OrderItemDTO orderItemDTO) {
        logger.info("Checking if orderItem exists");
        OrderItem initialOrderItem = orderItemRepository.findById(orderItemDTO.getId())
                .orElseThrow(() -> {
                    logger.error("OrderItem do not exists");
                    return new ResourceNotFoundException("Order Item", orderItemDTO.getId());
                });

        OrderItem orderItem = modelMapper.map(orderItemDTO, OrderItem.class);

        Order order = orderService.findById(orderItemDTO.getOrder());
        orderItem.setOrder(order);

        Product product = productService.findById(orderItemDTO.getProduct());
        orderItem.setProduct(product);

        orderItem.setOrderItemCustomizations(initialOrderItem.getOrderItemCustomizations());

        logger.info("Updating orderItem");
        return orderItemRepository.save(orderItem);
    }

    public void delete(Long id) {
        logger.info("Checking if orderItem exists");
        OrderItem orderItemEntity = orderItemRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("OrderItem do not exists");
                    return new ResourceNotFoundException("Order Item", id);
                });
        logger.info("Deleting orderItem");
        orderItemRepository.delete(orderItemEntity);
    }
}
