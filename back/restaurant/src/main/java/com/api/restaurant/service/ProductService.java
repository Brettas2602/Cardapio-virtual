package com.api.restaurant.service;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurant.DTO.ProductRequestDTO;
import com.api.restaurant.exception.ResourceNotFoundException;
import com.api.restaurant.model.Category;
import com.api.restaurant.model.Product;
import com.api.restaurant.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public List<Product> findAll() {
        logger.info("Searching all products");
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        logger.info("Searching for product with ID: " + id);
        return productRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Product with ID " + id + " not found");
                return new ResourceNotFoundException("Product", id);
            });
    }

    public Product getProductWithCustomizationsById(Long id) {
        logger.info("Searching for product and customizations with ID: " + id);
        return productRepository.getProductWithCustomizations(id)
            .orElseThrow(() -> {
                logger.error("Product with ID " + id + " not found");
                return new ResourceNotFoundException("Product", id);
            });
    }

    public List<Product> getAllProductsByCategoryId(Long id) {
        logger.info("Searching for all products with category ID: " + id);
        List<Product> products = productRepository.getProductByCategory(id);
        
        if (products.isEmpty()) {
            logger.error("Category with ID " + id + " not found");
            throw new ResourceNotFoundException("Category", id);
        }

        return products;
    }

    public Product create(ProductRequestDTO productRequestDTO) {
        logger.info("Creating product");

        Product product = modelMapper.map(productRequestDTO, Product.class);

        Set<Category> categories = categoryService.findAllById(productRequestDTO.getCategories());
        product.setCategories(categories);
        
        return productRepository.save(product);
    }

    public Product update(ProductRequestDTO productRequestDTO) {
        logger.info("Checking if product exists");
        Product initialProduct = productRepository.findById(productRequestDTO.getId())
            .orElseThrow(() -> {
                logger.error("Product do not exists");
                return new ResourceNotFoundException("Product", productRequestDTO.getId());
            });

        Product product = modelMapper.map(productRequestDTO, Product.class);

        Set<Category> categories = categoryService.findAllById(productRequestDTO.getCategories());
        product.setCategories(categories);
        
        product.setCartItem(initialProduct.getCartItem());
        product.setOrderItem(initialProduct.getOrderItem());
        product.setCustomizations(initialProduct.getCustomizations());

        logger.info("Updating product");
        return productRepository.save(product);
    }

    public void delete(Long id) {
        logger.info("Checking if product exists");
        Product productEntity = productRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Product do not exists");
                return new ResourceNotFoundException("Product", id);
            });
        logger.info("Deleting product");
        productRepository.delete(productEntity);
    }
}
