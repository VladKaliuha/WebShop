package com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.impl;

import com.epam.preproduction.kaliuha.constant.WebShopEntityField;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.RowMapper;
import com.epam.preproduction.kaliuha.entity.impl.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong(WebShopEntityField.ID),
                resultSet.getString(WebShopEntityField.FIRST_NAME),
                resultSet.getString(WebShopEntityField.LAST_NAME),
                resultSet.getString(WebShopEntityField.EMAIL),
                resultSet.getString(WebShopEntityField.CUSTOMER_PASSWORD),
                resultSet.getBoolean(WebShopEntityField.MAILING),
                resultSet.getString(WebShopEntityField.ICON)
        );
    }
}
