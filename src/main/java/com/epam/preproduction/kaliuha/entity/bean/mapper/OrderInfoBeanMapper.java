package com.epam.preproduction.kaliuha.entity.bean.mapper;

import com.epam.preproduction.kaliuha.constant.WebContextKey;
import com.epam.preproduction.kaliuha.entity.bean.OrderInfoBean;
import com.epam.preproduction.kaliuha.entity.cart.Cart;
import com.epam.preproduction.kaliuha.entity.impl.User;
import com.epam.preproduction.kaliuha.util.SessionAttributeExtractorUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderInfoBeanMapper {

    private static final String JUST_CREATED = "Just created";
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String CART = "cart";

    private DateTimeFormatter formatter;

    public OrderInfoBeanMapper() {
        formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    }

    public OrderInfoBean map(HttpServletRequest request) {
        User customer = SessionAttributeExtractorUtil.getAttribute(request, WebContextKey.USER);
        Cart cart = SessionAttributeExtractorUtil.getAttribute(request, CART);
        OrderInfoBean bean = new OrderInfoBean();
        bean.setPayment(request.getParameter("payment"));
        bean.setDelivery(request.getParameter("delivery"));
        bean.setCard(request.getParameter("card"));
        bean.setStatus(JUST_CREATED);
        bean.setDateTime(LocalDateTime.now().format(formatter));
        bean.setCustomerId(customer.getId());
        bean.setOrderedProducts(cart.getOrderedProducts());
        return bean;
    }
}
