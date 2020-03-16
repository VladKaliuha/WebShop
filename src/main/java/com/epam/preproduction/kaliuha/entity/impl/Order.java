package com.epam.preproduction.kaliuha.entity.impl;

import com.epam.preproduction.kaliuha.entity.Entity;

import java.util.List;
import java.util.Objects;

public class Order extends Entity {

    private String status;
    private String info;
    private String dateTime;
    private Long userId;
    private List<OrderedProduct> orderedProducts;

    public Order(long id, String status, String info, String dateTime, Long userId) {
        super(id);
        this.status = status;
        this.info = info;
        this.userId = userId;
        this.dateTime = dateTime;
    }

    public Order(String status, String info, String dateTime, Long userId) {
        this.status = status;
        this.info = info;
        this.userId = userId;
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "status='" + status + '\'' +
                ", info='" + info + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", userId=" + userId +
                ", orderedProducts=" + orderedProducts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(status, order.status) &&
                Objects.equals(info, order.info) &&
                Objects.equals(dateTime, order.dateTime) &&
                Objects.equals(userId, order.userId) &&
                Objects.equals(orderedProducts, order.orderedProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), status, info, dateTime, userId, orderedProducts);
    }
}
