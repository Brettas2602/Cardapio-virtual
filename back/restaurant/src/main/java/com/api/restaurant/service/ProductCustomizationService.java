package com.api.restaurant.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurant.DTO.ProductCustomizationDTO;
import com.api.restaurant.exception.ResourceNotFoundException;
import com.api.restaurant.model.Product;
import com.api.restaurant.model.ProductCustomization;
import com.api.restaurant.repository.ProductCustomizationRepository;

@Service
public class ProductCustomizationService {
    @Autowired
    private ProductCustomizationRepository productCustomizationRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProductCustomizationService.class);

    public ProductCustomization findById(Long id) {
        logger.info("Searching for productCustomization with ID: " + id);
        return productCustomizationRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("ProductCustomization with ID " + id + " not found");
                throw new ResourceNotFoundException("Product Customization", id);
            });
    }

    public ProductCustomization create(ProductCustomizationDTO productCustomizationDTO) {
        logger.info("Creating productCustomization");

        ProductCustomization productCustomization = modelMapper.map(productCustomizationDTO, ProductCustomization.class);
        
        Product product = productService.findById(productCustomizationDTO.getProduct());
        productCustomization.setProduct(product);

        return productCustomizationRepository.save(productCustomization);
    }

    public ProductCustomization update(ProductCustomizationDTO productCustomizationDTO) {
        logger.info("Checking if productCustomization exists");
        ProductCustomization initialCustomization = productCustomizationRepository.findById(productCustomizationDTO.getId())
            .orElseThrow(() -> {
                logger.error("ProductCustomization do not exists");
                return new ResourceNotFoundException("Product Customization", productCustomizationDTO.getId());
            });
        
        ProductCustomization productCustomization = modelMapper.map(productCustomizationDTO, ProductCustomization.class);
        
        Product product = productService.findById(productCustomizationDTO.getProduct());
        productCustomization.setProduct(product);

        productCustomization.setCustomizationValues(initialCustomization.getCustomizationValues());
        
        logger.info("Updating productCustomization");
        return productCustomizationRepository.save(productCustomization);
    }

    public void delete(Long id) {
        logger.info("Checking if productCustomization exists");
        ProductCustomization productCustomizationEntity = productCustomizationRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("ProductCustomization do not exists");
                return new ResourceNotFoundException("Product Customization", id);
            });
        logger.info("Deleting productCustomization");
        productCustomizationRepository.delete(productCustomizationEntity);
    }
}
