package com.epam.preproduction.kaliuha.entity.bean.mapper;

import com.epam.preproduction.kaliuha.dto.ProductFilterDto;
import com.epam.preproduction.kaliuha.entity.bean.ProductFilterBean;

public class FilterBeanMapper {

    public ProductFilterBean map(ProductFilterDto dto) {
        ProductFilterBean filterBean = new ProductFilterBean();
        filterBean.setProductName(dto.getProductName());
        filterBean.setProductCategory(dto.getProductCategory());
        filterBean.setProductFabricator(dto.getProductFabricator());
        filterBean.setMaxPrice(dto.getMaxPrice());
        filterBean.setMinPrice(dto.getMinPrice());
        return filterBean;
    }
}
