package com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.impl;

import com.epam.preproduction.kaliuha.constant.WebShopEntityField;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.RowMapper;
import com.epam.preproduction.kaliuha.entity.impl.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getLong(WebShopEntityField.ID),
                resultSet.getString(WebShopEntityField.NAME),
                resultSet.getString(WebShopEntityField.FABRICATOR),
                resultSet.getString(WebShopEntityField.CATEGORY),
                resultSet.getDouble(WebShopEntityField.PRICE),
                resultSet.getString(WebShopEntityField.INFO),
                resultSet.getString(WebShopEntityField.ICON)
        );
    }
}
