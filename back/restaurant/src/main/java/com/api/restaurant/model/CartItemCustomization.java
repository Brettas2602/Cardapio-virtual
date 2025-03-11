package com.api.restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_item_customizations")
public class CartItemCustomization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_item_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private CartItem cartItem;

    @ManyToOne
    @JoinColumn(name = "customization_value_id", referencedColumnName = "id", nullable = false)
    private ProductCustomizationValue customizationValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }

    public ProductCustomizationValue getCustomizationValue() {
        return customizationValue;
    }

    public void setCustomizationValue(ProductCustomizationValue customizationValue) {
        this.customizationValue = customizationValue;
    }

    
}
