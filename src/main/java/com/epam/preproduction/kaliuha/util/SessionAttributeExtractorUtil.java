package com.epam.preproduction.kaliuha.util;

import com.epam.preproduction.kaliuha.entity.cart.Cart;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public final class SessionAttributeExtractorUtil {

    public SessionAttributeExtractorUtil() {
        throw new UnsupportedOperationException();
    }

    public static Cart getCart(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (Objects.isNull(cart)) {
            cart = new Cart();
        }
        return cart;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getAttribute(HttpServletRequest request, String name) {
        return (T) request.getSession().getAttribute(name);
    }
}
