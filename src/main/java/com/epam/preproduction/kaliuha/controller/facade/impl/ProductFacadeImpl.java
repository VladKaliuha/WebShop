package com.epam.preproduction.kaliuha.controller.facade.impl;

import com.epam.preproduction.kaliuha.controller.facade.ProductFacade;
import com.epam.preproduction.kaliuha.dto.PaginationDto;
import com.epam.preproduction.kaliuha.dto.ShoppingPageItems;
import com.epam.preproduction.kaliuha.entity.bean.ProductFilterBean;
import com.epam.preproduction.kaliuha.entity.bean.mapper.FilterBeanMapper;
import com.epam.preproduction.kaliuha.service.category.CategoryService;
import com.epam.preproduction.kaliuha.service.fabricator.FabricatorService;
import com.epam.preproduction.kaliuha.service.product.ProductService;

public class ProductFacadeImpl implements ProductFacade {

    private ProductService productService;
    private FabricatorService fabricatorService;
    private CategoryService categoryService;

    public ProductFacadeImpl(ProductService productService, FabricatorService fabricatorService, CategoryService categoryService) {
        this.productService = productService;
        this.fabricatorService = fabricatorService;
        this.categoryService = categoryService;
    }

    @Override
    public ShoppingPageItems getProducts(ProductFilterBean filterBean, PaginationDto paginationParam, String sortingRule) {
        return new ShoppingPageItems(
                categoryService.getAllCategories(),
                fabricatorService.getAllFabricators(),
                productService.getAllProducts(filterBean, paginationParam, sortingRule));
    }

    @Override
    public long getPageCount(ProductFilterBean filterBean, PaginationDto paginationParam) {
        Long allProductsCount = productService.getProductsCount(filterBean);
        long pageCount = allProductsCount / paginationParam.getCount();
        if (allProductsCount % paginationParam.getCount() != 0) {
            pageCount++;
        }
        return pageCount;
    }
}
