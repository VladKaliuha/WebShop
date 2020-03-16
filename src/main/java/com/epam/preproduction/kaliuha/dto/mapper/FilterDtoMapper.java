package com.epam.preproduction.kaliuha.dto.mapper;

import com.epam.preproduction.kaliuha.constant.WebShopEntityField;
import com.epam.preproduction.kaliuha.dto.ProductFilterDto;

import javax.servlet.http.HttpServletRequest;

public class FilterDtoMapper {

    public ProductFilterDto map(HttpServletRequest request) {
        ProductFilterDto filterDto = new ProductFilterDto();
        filterDto.setProductName(request.getParameter(WebShopEntityField.PRODUCT_NAME_JSP_PARAM));
        filterDto.setProductCategory(request.getParameterValues(WebShopEntityField.CATEGORY_JSP_PARAM));
        filterDto.setProductFabricator(request.getParameterValues(WebShopEntityField.FABRICATOR_JSP_PARAM));
        filterDto.setMinPrice(request.getParameter(WebShopEntityField.MIN_PRICE_JSP_PARAM));
        filterDto.setMaxPrice(request.getParameter(WebShopEntityField.MAX_PRICE_JSP_PARAM));
        return filterDto;
    }
}
