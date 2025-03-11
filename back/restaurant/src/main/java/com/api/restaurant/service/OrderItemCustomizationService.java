package com.api.restaurant.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurant.DTO.OrderItemCustomizationDTO;
import com.api.restaurant.exception.ResourceNotFoundException;
import com.api.restaurant.model.OrderItem;
import com.api.restaurant.model.OrderItemCustomization;
import com.api.restaurant.model.ProductCustomizationValue;
import com.api.restaurant.repository.OrderItemCustomizationRepository;

@Service
public class OrderItemCustomizationService {
    @Autowired
    private OrderItemCustomizationRepository orderItemCustomizationRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductCustomizationValueService productCustomizationValueService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(OrderItemCustomizationService.class);

    public OrderItemCustomization create(OrderItemCustomizationDTO orderItemCustomizationDTO) {
        logger.info("Creating orderItemCustomization");

        OrderItemCustomization orderItemCustomization = modelMapper.map(orderItemCustomizationDTO, OrderItemCustomization.class);

        OrderItem orderItem = orderItemService.findById(orderItemCustomizationDTO.getOrderItem());
        orderItemCustomization.setOrderItem(orderItem);

        ProductCustomizationValue productCustomizationValue = productCustomizationValueService.findById(orderItemCustomizationDTO.getCustomizationValue());
        orderItemCustomization.setCustomizationValue(productCustomizationValue);

        return orderItemCustomizationRepository.save(orderItemCustomization);
    }

    public OrderItemCustomization update(OrderItemCustomizationDTO orderItemCustomizationDTO) {
        logger.info("Checking if orderItemCustomization exists");
        orderItemCustomizationRepository.findById(orderItemCustomizationDTO.getId())
            .orElseThrow(() -> {
                logger.error("OrderItemCustomization do not exists");
                return new ResourceNotFoundException("Order Item Customization", orderItemCustomizationDTO.getId());
            });

        OrderItemCustomization orderItemCustomization = modelMapper.map(orderItemCustomizationDTO, OrderItemCustomization.class);

        OrderItem orderItem = orderItemService.findById(orderItemCustomizationDTO.getOrderItem());
        orderItemCustomization.setOrderItem(orderItem);

        ProductCustomizationValue productCustomizationValue = productCustomizationValueService.findById(orderItemCustomizationDTO.getCustomizationValue());
        orderItemCustomization.setCustomizationValue(productCustomizationValue);

        logger.info("Updating orderItemCustomization");
        return orderItemCustomizationRepository.save(orderItemCustomization);
    }

    public void delete(Long id) {
        logger.info("Checking if orderItemCustomization exists");
        OrderItemCustomization orderItemCustomizationEntity = orderItemCustomizationRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("OrderItemCustomization do not exists");
                return new ResourceNotFoundException("Order Item Customization", id);
            });
        logger.info("Deleting orderItemCustomization");
        orderItemCustomizationRepository.delete(orderItemCustomizationEntity);
    }
}
