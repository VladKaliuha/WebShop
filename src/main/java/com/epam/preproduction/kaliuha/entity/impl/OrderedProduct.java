package com.epam.preproduction.kaliuha.entity.impl;

import com.epam.preproduction.kaliuha.entity.Entity;

import java.util.Objects;

public final class OrderedProduct extends Entity {

    private Long productId;
    private Integer count;
    private Double productPrice;
    private Long orderId;

    public OrderedProduct(long id, Long productId, Integer count, Double productPrice, Long orderId) {
        super(id);
        this.productId = productId;
        this.count = count;
        this.productPrice = productPrice;
        this.orderId = orderId;
    }

    public OrderedProduct(Long productId, Integer count, Double productPrice, Long orderId) {
        this.productId = productId;
        this.count = count;
        this.productPrice = productPrice;
        this.orderId = orderId;
    }

    public OrderedProduct(Long productId, Integer count, Double productPrice) {
        this.productId = productId;
        this.count = count;
        this.productPrice = productPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getCount() {
        return count;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderedProduct{" +
                "productId=" + productId +
                ", count=" + count +
                ", productPrice=" + productPrice +
                ", orderId=" + orderId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderedProduct that = (OrderedProduct) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(count, that.count) &&
                Objects.equals(productPrice, that.productPrice) &&
                Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), productId, count, productPrice, orderId);
    }
}
