package com.epam.preproduction.kaliuha.service.order.impl;

import com.epam.preproduction.kaliuha.dao.order.OrderDao;
import com.epam.preproduction.kaliuha.dataBase.transaction.TransactionManager;
import com.epam.preproduction.kaliuha.entity.bean.OrderInfoBean;
import com.epam.preproduction.kaliuha.service.order.OrderService;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private TransactionManager transactionManager;

    public OrderServiceImpl(OrderDao orderDao, TransactionManager transactionManager) {
        this.orderDao = orderDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public void addOrder(OrderInfoBean orderInfoBean) {
        transactionManager.doTransaction(() ->
                orderDao.addOrder(orderInfoBean).ifPresent(
                        orderId -> orderInfoBean.getOrderedProducts()
                                .forEach(orderedProduct -> {
                                    orderedProduct.setOrderId(orderId);
                                    orderDao.addProduct(orderedProduct);
                                })
                )
        );
    }
}
