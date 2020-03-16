package com.epam.preproduction.kaliuha.controller.helper;

import com.epam.preproduction.kaliuha.dto.ShoppingPageItems;
import com.epam.preproduction.kaliuha.constant.WebContextKey;
import com.epam.preproduction.kaliuha.constant.WebShopEntityField;
import com.epam.preproduction.kaliuha.entity.bean.RegistrationBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public final class ServletHelper {

    private ServletHelper() {
        throw new UnsupportedOperationException();
    }

    public static void prepareRequestToSaveRegistrationData(HttpServletRequest request, RegistrationBean bean, List<String> errorMessages) {
        request.setAttribute(WebContextKey.ERROR_MESSAGES, errorMessages);
        request.setAttribute(WebShopEntityField.FIRST_NAME, bean.getFirstName());
        request.setAttribute(WebShopEntityField.LAST_NAME, bean.getLastName());
        request.setAttribute(WebShopEntityField.EMAIL, bean.getEmail());
    }

    public static void prepareRequestToSaveLoginData(HttpServletRequest request, String email, String errorMessages) {
        request.setAttribute(WebContextKey.ERROR_MESSAGES, errorMessages);
        request.setAttribute(WebShopEntityField.EMAIL, email);
    }
}
