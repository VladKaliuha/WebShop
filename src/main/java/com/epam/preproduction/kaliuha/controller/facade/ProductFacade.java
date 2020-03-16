package com.epam.preproduction.kaliuha.controller.facade;

import com.epam.preproduction.kaliuha.dto.PaginationDto;
import com.epam.preproduction.kaliuha.dto.ShoppingPageItems;
import com.epam.preproduction.kaliuha.entity.bean.ProductFilterBean;

public interface ProductFacade {

    ShoppingPageItems getProducts(ProductFilterBean filterDto, PaginationDto paginationParam, String sortingRule);

    long getPageCount(ProductFilterBean filterDto, PaginationDto paginationParam);
}
