package com.api.restaurant.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_customization_values")
public class ProductCustomizationValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "additional_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal additionalPrice;

    @ManyToOne
    @JoinColumn(name = "customization_id", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    private ProductCustomization productCustomizations;

    @OneToMany(mappedBy = "customizationValue", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<OrderItemCustomization> orderItemCustomization;

    @OneToMany(mappedBy = "customizationValue", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<CartItemCustomization> cartItemCustomization;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCustomization getProductCustomizations() {
        return productCustomizations;
    }

    public void setProductCustomizations(ProductCustomization productCustomizations) {
        this.productCustomizations = productCustomizations;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<CartItemCustomization> getCartItemCustomization() {
        return cartItemCustomization;
    }

    public void setCartItemCustomization(Set<CartItemCustomization> cartItemCustomization) {
        this.cartItemCustomization = cartItemCustomization;
    }

    public Set<OrderItemCustomization> getOrderItemCustomization() {
        return orderItemCustomization;
    }

    public void setOrderItemCustomization(Set<OrderItemCustomization> orderItemCustomization) {
        this.orderItemCustomization = orderItemCustomization;
    }
    
}
