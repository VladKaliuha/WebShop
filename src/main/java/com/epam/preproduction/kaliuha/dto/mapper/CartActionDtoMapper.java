package com.epam.preproduction.kaliuha.dto.mapper;

import com.epam.preproduction.kaliuha.dto.CartActionDto;

import javax.servlet.http.HttpServletRequest;

public class CartActionDtoMapper {

    public CartActionDto map(HttpServletRequest request) {
        CartActionDto cartActionDto = new CartActionDto();
        cartActionDto.setAction(request.getParameter("action"));
        cartActionDto.setProductId(request.getParameter("product_id"));
        return cartActionDto;
    }
}
