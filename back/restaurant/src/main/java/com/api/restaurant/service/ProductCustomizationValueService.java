package com.api.restaurant.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurant.DTO.ProductCustomizationValueDTO;
import com.api.restaurant.exception.ResourceNotFoundException;
import com.api.restaurant.model.ProductCustomization;
import com.api.restaurant.model.ProductCustomizationValue;
import com.api.restaurant.repository.ProductCustomizationValueRepository;



@Service
public class ProductCustomizationValueService {
    @Autowired
    private ProductCustomizationValueRepository productCustomizationValueRepository;

    @Autowired
    private ProductCustomizationService productCustomizationService;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProductCustomizationValueService.class);

    public ProductCustomizationValue findById(Long id) {
        logger.info("Searching for productCustomizationValue with ID: " + id);
        return productCustomizationValueRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("ProductCustomizationValue with ID " + id + " not found");
                throw new ResourceNotFoundException("Product Customization Value", id);
            });
    }

    public ProductCustomizationValue create(ProductCustomizationValueDTO productCustomizationValueDTO) {
        logger.info("Creating productCustomizationValue");

        ProductCustomizationValue productCustomizationValue = modelMapper.map(productCustomizationValueDTO, ProductCustomizationValue.class);

        ProductCustomization productCustomization = productCustomizationService.findById(productCustomizationValueDTO.getProductCustomizations());
        productCustomizationValue.setProductCustomizations(productCustomization);

        return productCustomizationValueRepository.save(productCustomizationValue);
    }

    public ProductCustomizationValue update(ProductCustomizationValueDTO productCustomizationValueDTO) {
        logger.info("Checking if productCustomizationValue exists");
        ProductCustomizationValue initialCustomizationValue = productCustomizationValueRepository.findById(productCustomizationValueDTO.getId())
            .orElseThrow(() -> {
                logger.error("ProductCustomizationValue do not exists");
                return new ResourceNotFoundException("Product Customization Value", productCustomizationValueDTO.getId());
            });
        
        ProductCustomizationValue productCustomizationValue = modelMapper.map(productCustomizationValueDTO, ProductCustomizationValue.class);

        ProductCustomization productCustomization = productCustomizationService.findById(productCustomizationValueDTO.getProductCustomizations());
        productCustomizationValue.setProductCustomizations(productCustomization);

        productCustomizationValue.setCartItemCustomization(initialCustomizationValue.getCartItemCustomization());
        productCustomizationValue.setOrderItemCustomization(initialCustomizationValue.getOrderItemCustomization());
        
        logger.info("Updating productCustomizationValue");
        return productCustomizationValueRepository.save(productCustomizationValue);
    }

    public void delete(Long id) {
        logger.info("Checking if productCustomizationValue exists");
        ProductCustomizationValue productCustomizationValueEntity = productCustomizationValueRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("ProductCustomizationValue do not exists");
                return new ResourceNotFoundException("Product Customization Value", id);
            });
        logger.info("Deleting productCustomizationValue");
        productCustomizationValueRepository.delete(productCustomizationValueEntity);
    }
}
