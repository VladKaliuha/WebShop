package com.epam.preproduction.kaliuha.entity.bean;

import java.util.Arrays;
import java.util.Objects;

public class ProductFilterBean {

    private String productName;
    private String[] productCategory;
    private String[] productFabricator;
    private String minPrice;
    private String maxPrice;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCategory(String[] productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductFabricator(String[] productFabricator) {
        this.productFabricator = productFabricator;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String[] getProductCategory() {
        return productCategory;
    }

    public String[] getProductFabricator() {
        return productFabricator;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    @Override
    public String toString() {
        return "ProductFilterBean{" +
                "productName='" + productName + '\'' +
                ", productCategory=" + Arrays.toString(productCategory) +
                ", productFabricator=" + Arrays.toString(productFabricator) +
                ", minPrice='" + minPrice + '\'' +
                ", maxPrice='" + maxPrice + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductFilterBean that = (ProductFilterBean) o;
        return Objects.equals(productName, that.productName) &&
                Arrays.equals(productCategory, that.productCategory) &&
                Arrays.equals(productFabricator, that.productFabricator) &&
                Objects.equals(minPrice, that.minPrice) &&
                Objects.equals(maxPrice, that.maxPrice);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(productName, minPrice, maxPrice);
        result = 31 * result + Arrays.hashCode(productCategory);
        result = 31 * result + Arrays.hashCode(productFabricator);
        return result;
    }
}
