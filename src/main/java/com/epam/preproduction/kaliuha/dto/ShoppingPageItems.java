package com.epam.preproduction.kaliuha.dto;

import com.epam.preproduction.kaliuha.entity.impl.Category;
import com.epam.preproduction.kaliuha.entity.impl.Fabricator;
import com.epam.preproduction.kaliuha.entity.impl.Product;

import java.util.List;

public class ShoppingPageItems {

    private List<Category> categories;
    private List<Fabricator> fabricators;
    private List<Product> products;

    public ShoppingPageItems(List<Category> categories, List<Fabricator> fabricators, List<Product> products) {
        this.categories = categories;
        this.fabricators = fabricators;
        this.products = products;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Fabricator> getFabricators() {
        return fabricators;
    }

    public List<Product> getProducts() {
        return products;
    }
}
