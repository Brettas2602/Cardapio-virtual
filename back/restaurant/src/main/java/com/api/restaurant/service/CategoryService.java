package com.api.restaurant.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurant.exception.ResourceNotFoundException;
import com.api.restaurant.model.Category;
import com.api.restaurant.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    public List<Category> findAll() {
        logger.info("Searching all categories");
        return categoryRepository.findAll();
    }

    public Set<Category> findAllById(Set<Long> ids) {
        logger.info("Searching all categories with ID: " + ids);
        Set<Category> categories = categoryRepository.findAllById(ids).stream().collect(Collectors.toSet());
        if (categories.size() != ids.size()) {
            logger.error("One or more categories not found");
            throw new ResourceNotFoundException("One or more categories not found");
        }
        return categories;
    }

    public Category create(Category category) {
        logger.info("Creating category");
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        logger.info("Checking if category exists");
        Category initialCategory = categoryRepository.findById(category.getId())
            .orElseThrow(() -> {
                logger.error("Category do not exists");
                return new ResourceNotFoundException("Category", category.getId());
            });

        category.setProducts(initialCategory.getProducts());

        logger.info("Updating category");
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        logger.info("Checking if category exists");
        Category categoryEntity = categoryRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Category do not exists");
                return new ResourceNotFoundException("Category", id);
            });
        logger.info("Deleting category");
        categoryRepository.delete(categoryEntity);
    }
}
