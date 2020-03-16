package com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.impl;

import com.epam.preproduction.kaliuha.dataBase.connector.DBConnectionManager;
import com.epam.preproduction.kaliuha.dataBase.constant.WebShopDBMessage;
import com.epam.preproduction.kaliuha.dataBase.exception.DaoException;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.JdbcTemplate;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.RowMapper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateImpl implements JdbcTemplate {

    private static final Logger LOG = Logger.getLogger(JdbcTemplateImpl.class);
    public static final String COUNT = "count";

    private DBConnectionManager connectionManager;

    public JdbcTemplateImpl(DBConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public <T> Optional<T> queryForObject(String sql, RowMapper<T> mapper, Object... args) throws DaoException {
        Optional<T> optional = Optional.empty();
        try {
            Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                optional = Optional.of(mapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOG.warn(WebShopDBMessage.ERR_CANNOT_OBTAIN_USER);
        }
        return optional;
    }

    @Override
    public <T> List<T> queryForList(String sql, RowMapper<T> mapper, Object... args) throws DaoException {
        List<T> items = new ArrayList<>();
        try {
            Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                items.add(
                        mapper.mapRow(resultSet)
                );
            }
        } catch (SQLException e) {
            LOG.warn(WebShopDBMessage.ERR_CANNOT_OBTAIN_USER);
        }
        return items;
    }

    @Override
    public Optional<Long> update(String sql, Object... args) {
        Optional<Long> id = Optional.empty();
        try {
            Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = Optional.of(resultSet.getLong(1));
            }
            connection.commit();
        } catch (SQLException e) {
            LOG.warn(WebShopDBMessage.ERR_CANNOT_INSERT_USER);
            throw new DaoException();
        }
        return id;
    }

    @Override
    public <T> T execute(String sql) throws DaoException {
        try {
            Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return (T) resultSet.getObject(COUNT);
            }
        } catch (SQLException e) {
            LOG.warn(WebShopDBMessage.ERR_CANNOT_OBTAIN_USER);
        }
        return null;
    }
}
