package com.api.restaurant.DTO;

import java.math.BigDecimal;

public class CartItemDTO {
    private Long id;
    private Integer quantity;
    private BigDecimal totalPrice;
    private Long cart;
    private Long product;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Long getCart() {
        return cart;
    }
    public void setCart(Long cart) {
        this.cart = cart;
    }
    public Long getProduct() {
        return product;
    }
    public void setProduct(Long product) {
        this.product = product;
    }
}
