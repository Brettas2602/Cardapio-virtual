package com.api.restaurant.DTO;

public class ProductCustomizationDTO {
    private Long id;
    private String name;
    private String selectionType;
    private Integer maxSelections;
    private Boolean isRequired;
    private Long product;

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
    public String getSelectionType() {
        return selectionType;
    }
    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }
    public Integer getMaxSelections() {
        return maxSelections;
    }
    public void setMaxSelections(Integer maxSelections) {
        this.maxSelections = maxSelections;
    }
    public Long getProduct() {
        return product;
    }
    public void setProduct(Long product) {
        this.product = product;
    }
    public Boolean getIsRequired() {
        return isRequired;
    }
    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    
}
