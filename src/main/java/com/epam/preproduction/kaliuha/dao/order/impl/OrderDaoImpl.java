package com.epam.preproduction.kaliuha.dao.order.impl;

import com.epam.preproduction.kaliuha.dao.order.OrderDao;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.JdbcTemplate;
import com.epam.preproduction.kaliuha.entity.bean.OrderInfoBean;
import com.epam.preproduction.kaliuha.entity.impl.OrderedProduct;

import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    private static final String SQL_INSERT_INTO_ORDER = "INSERT INTO webshopdb.order(status, info, date_time, user_id) VALUES (?, ?, ?, ?)";
    private static final String SQL_INSERT_INTO_ORDERED_PRODUCT = "INSERT INTO webshopdb.ordered_product(product_id, count, price, order_id) VALUES (?, ?, ?, ?)";

    private JdbcTemplate jdbcTemplate;

    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Long> addOrder(OrderInfoBean orderInfoBean) {
        return jdbcTemplate.update(SQL_INSERT_INTO_ORDER, orderInfoBean.getStatus(), orderInfoBean.getInfo(), orderInfoBean.getDateTime(), orderInfoBean.getCustomerId());
    }

    @Override
    public void addProduct(OrderedProduct orderedProduct) {
        jdbcTemplate.update(SQL_INSERT_INTO_ORDERED_PRODUCT, orderedProduct.getProductId(), orderedProduct.getCount(), orderedProduct.getProductPrice(), orderedProduct.getOrderId());
    }
}
