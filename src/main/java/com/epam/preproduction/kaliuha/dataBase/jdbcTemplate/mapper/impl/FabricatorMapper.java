package com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.impl;

import com.epam.preproduction.kaliuha.constant.WebShopEntityField;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.RowMapper;
import com.epam.preproduction.kaliuha.entity.impl.Fabricator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FabricatorMapper implements RowMapper<Fabricator> {

    @Override
    public Fabricator mapRow(ResultSet resultSet) throws SQLException {
        return new Fabricator(
                resultSet.getLong(WebShopEntityField.ID),
                resultSet.getString(WebShopEntityField.NAME)
        );
    }
}
