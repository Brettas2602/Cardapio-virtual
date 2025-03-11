package com.api.restaurant.DTO;

import java.math.BigDecimal;

public class ProductCustomizationValueDTO {
    private Long id;
    private String name;
    private BigDecimal additionalPrice;
    private Long productCustomizations;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getAdditionalPrice() {
        return additionalPrice;
    }
    public void setAdditionalPrice(BigDecimal additionalPrice) {
        this.additionalPrice = additionalPrice;
    }
    public Long getProductCustomizations() {
        return productCustomizations;
    }
    public void setProductCustomizations(Long productCustomizations) {
        this.productCustomizations = productCustomizations;
    }

    
}
