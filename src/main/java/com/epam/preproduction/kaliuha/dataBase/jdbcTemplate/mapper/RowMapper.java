package com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T>{

    T mapRow(ResultSet resultSet) throws SQLException;
}
