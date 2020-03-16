package com.epam.preproduction.kaliuha.dataBase.jdbcTemplate;

import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.RowMapper;

import java.util.List;
import java.util.Optional;

public interface JdbcTemplate {

    <T> Optional<T> queryForObject(String sql, RowMapper<T> mapper, Object... args);

    <T> List<T> queryForList(String sql, RowMapper<T> mapper, Object... args);

    Optional<Long> update(String sql, Object... args);

    <T> T execute(String sql);
}
