package com.api.restaurant.DTO;

public class CartItemCustomizationDTO {
    private Long id;
    private Long cartItem;
    private Long customizationValue;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCartItem() {
        return cartItem;
    }
    public void setCartItem(Long cartItem) {
        this.cartItem = cartItem;
    }
    public Long getCustomizationValue() {
        return customizationValue;
    }
    public void setCustomizationValue(Long customizationValue) {
        this.customizationValue = customizationValue;
    }

    
}
