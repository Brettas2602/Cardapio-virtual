package com.api.restaurant.DTO;

import java.math.BigDecimal;

public class OrderItemDTO {
    private Long id;
    private Integer quantity;
    private BigDecimal totalPrice;
    private Long order;
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
    public Long getOrder() {
        return order;
    }
    public void setOrder(Long order) {
        this.order = order;
    }
    public Long getProduct() {
        return product;
    }
    public void setProduct(Long product) {
        this.product = product;
    }

    
}
