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
@Table(name = "order_item_customizations")
public class OrderItemCustomization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_item_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private OrderItem orderItem;

    @ManyToOne
    @JoinColumn(name = "customization_value_id", referencedColumnName = "id", nullable = false)
    private ProductCustomizationValue customizationValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public ProductCustomizationValue getCustomizationValue() {
        return customizationValue;
    }

    public void setCustomizationValue(ProductCustomizationValue customizationValue) {
        this.customizationValue = customizationValue;
    }
    
}
