package com.api.restaurant.DTO;

public class OrderItemCustomizationDTO {
    private Long id;
    private Long orderItem;
    private Long customizationValue;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderItem() {
        return orderItem;
    }
    public void setOrderItem(Long orderItem) {
        this.orderItem = orderItem;
    }
    public Long getCustomizationValue() {
        return customizationValue;
    }
    public void setCustomizationValue(Long customizationValue) {
        this.customizationValue = customizationValue;
    } 
}
