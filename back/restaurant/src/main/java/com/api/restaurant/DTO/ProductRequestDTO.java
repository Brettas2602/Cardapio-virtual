package com.api.restaurant.DTO;

import java.math.BigDecimal;
import java.util.Set;

public class ProductRequestDTO {
    private Long id;
    private String name;
    private BigDecimal basePrice;
    private String description;
    private String type;
    private String imageUrl;
    private Set<Long> categories;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getBasePrice() {
        return basePrice;
    }
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Set<Long> getCategories() {
        return categories;
    }
    public void setCategories(Set<Long> categories) {
        this.categories = categories;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    
}
