package com.epam.preproduction.kaliuha.dao.product;

import com.epam.preproduction.kaliuha.dao.product.wrapper.PageableResultBuilder;
import com.epam.preproduction.kaliuha.entity.bean.ProductFilterBean;
import com.epam.preproduction.kaliuha.entity.impl.Product;

import java.util.Optional;

public interface ProductDao {

    PageableResultBuilder getAllProducts(ProductFilterBean filterBean);

    Optional<Product> getProduct(long id);

    Long getProductsCount(ProductFilterBean filterQuery);

    void addProduct(Product product);
}
