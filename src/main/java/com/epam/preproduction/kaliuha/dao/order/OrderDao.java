package com.epam.preproduction.kaliuha.dao.order;

import com.epam.preproduction.kaliuha.dao.Dao;
import com.epam.preproduction.kaliuha.entity.bean.OrderInfoBean;
import com.epam.preproduction.kaliuha.entity.impl.OrderedProduct;

import java.util.Optional;

public interface OrderDao extends Dao {

    Optional<Long> addOrder(OrderInfoBean orderInfoBean);

    void addProduct(OrderedProduct orderedProduct);
}
