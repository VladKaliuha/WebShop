package com.epam.preproduction.kaliuha.entity.impl;

import com.epam.preproduction.kaliuha.entity.Entity;

import java.util.Objects;

public class Product extends Entity {

    private String name;
    private String fabricator;
    private String category;
    private Double price;
    private String info;
    private String icon;

    public Product(long id, String name, String fabricator, String category, Double price, String info, String icon) {
        super(id);
        this.name = name;
        this.fabricator = fabricator;
        this.category = category;
        this.price = price;
        this.info = info;
        this.icon = icon;
    }

    public Product(String name, String fabricator, String category, Double price, String info, String icon) {
        this.name = name;
        this.fabricator = fabricator;
        this.category = category;
        this.price = price;
        this.info = info;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getFabricator() {
        return fabricator;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public String getInfo() {
        return info;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(fabricator, product.fabricator) &&
                Objects.equals(category, product.category) &&
                Objects.equals(price, product.price) &&
                Objects.equals(info, product.info) &&
                Objects.equals(icon, product.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, fabricator, category, price, info, icon);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", fabricator='" + fabricator + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", info='" + info + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
