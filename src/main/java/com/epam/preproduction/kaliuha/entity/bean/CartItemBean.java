package com.epam.preproduction.kaliuha.entity.bean;

import com.epam.preproduction.kaliuha.entity.impl.Product;
import org.apache.commons.math3.util.Precision;

public class CartItemBean {

    private Product product;
    private Integer count;
    private double totalPrice;

    public CartItemBean(Product product, Integer count) {
        this.product = product;
        this.count = count;
        this.totalPrice = product.getPrice() * count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getTotalPrice() {
        return Precision.round(totalPrice, 2);
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
