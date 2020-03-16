package com.epam.preproduction.kaliuha.service.product;

import com.epam.preproduction.kaliuha.dto.PaginationDto;
import com.epam.preproduction.kaliuha.entity.bean.ProductFilterBean;
import com.epam.preproduction.kaliuha.entity.impl.Product;
import com.epam.preproduction.kaliuha.service.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService extends Service {

    /**
     * Return product by id
     *
     * @param id product id
     * @return suitable product
     */
    Optional<Product> getProduct(long id);


    /**
     * Return products right to filters
     *
     * @param filterBean      all filters
     * @param paginationParam page pagination
     * @param sortingRule     sorting principle
     * @return all products by user's parameters
     */
    List<Product> getAllProducts(ProductFilterBean filterBean, PaginationDto paginationParam, String sortingRule);

    /**
     * Return counts of products right to filters
     *
     * @param sql filters that customer use
     * @return products count
     */
    Long getProductsCount(ProductFilterBean sql);

    void addProduct(Product product);
}
