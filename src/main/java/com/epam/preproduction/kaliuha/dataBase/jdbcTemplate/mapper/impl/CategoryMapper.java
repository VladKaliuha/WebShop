package com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.impl;

import com.epam.preproduction.kaliuha.constant.WebShopEntityField;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.RowMapper;
import com.epam.preproduction.kaliuha.entity.impl.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet resultSet) throws SQLException {
        return new Category(
                resultSet.getLong(WebShopEntityField.ID),
                resultSet.getString(WebShopEntityField.NAME)
        );
    }
}
