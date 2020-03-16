package com.epam.preproduction.kaliuha.dto;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;

public class ProductFilterDto {

    private static final String DECIMAL_REGEX = "\\d+";
    private static final String MIN_PRICE = "0";
    private static final String MAX_PRICE = "";


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
        if (StringUtils.isNotBlank(minPrice) && minPrice.matches(DECIMAL_REGEX)) {
            this.minPrice = minPrice;
        } else {
            this.minPrice = MIN_PRICE;
        }
    }

    public void setMaxPrice(String maxPrice) {
        if (StringUtils.isNotBlank(minPrice) && minPrice.matches(DECIMAL_REGEX)) {
            this.maxPrice = maxPrice;
        } else {
            this.maxPrice = MAX_PRICE;
        }
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductFilterDto that = (ProductFilterDto) o;
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
}
