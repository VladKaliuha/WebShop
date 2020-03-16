package com.epam.preproduction.kaliuha.entity.cart;

import com.epam.preproduction.kaliuha.entity.bean.CartItemBean;
import com.epam.preproduction.kaliuha.entity.impl.OrderedProduct;
import com.epam.preproduction.kaliuha.entity.impl.Product;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cart {

    private Map<Product, Integer> cart;

    public Cart() {
        this.cart = new LinkedHashMap<>();
    }

    public void addProduct(Product product, Integer addedCount) {
        int amount = getProductCount(product) + addedCount;
        if (amount <= 0) {
            removeProduct(product);
        } else {
            cart.put(product, amount);
        }
    }

    private Integer getProductCount(Product product) {
        return cart.getOrDefault(product, 0);
    }

    public List<OrderedProduct> getOrderedProducts() {
        return cart.entrySet().stream()
                .map((cartItem) -> new OrderedProduct(cartItem.getKey().getId(), cartItem.getValue(), cartItem.getKey().getPrice()))
                .collect(Collectors.toList());
    }

    public List<CartItemBean> getProductsFromCart() {
        return cart.entrySet().stream()
                .map((cartItem) -> new CartItemBean(cartItem.getKey(), cartItem.getValue()))
                .collect(Collectors.toList());
    }

    public void clear() {
        cart.clear();
    }

    public void removeProduct(Product product) {
        cart.remove(product);
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public Integer getCartSize() {
        return cart.size();
    }

    public double getTotalPrice() {
        return cart.entrySet().stream()
                .mapToDouble(entry -> (entry.getKey().getPrice() * entry.getValue()))
                .sum();
    }
}
