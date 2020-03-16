package com.epam.preproduction.kaliuha.dao.user.impl;

import com.epam.preproduction.kaliuha.dao.user.UserDao;
import com.epam.preproduction.kaliuha.dataBase.exception.DaoException;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.JdbcTemplate;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.RowMapper;
import com.epam.preproduction.kaliuha.entity.impl.User;
import org.apache.log4j.Logger;

import java.util.Optional;

public class UserDaoDB implements UserDao {

    private static final Logger LOG = Logger.getLogger(UserDaoDB.class);

    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM user WHERE email=?";
    private static final String SQL_INSERT_INTO_USER = "INSERT INTO webshopdb.user(first_name, last_name, email, password, mailing, icon) VALUES (?, ?, ?, ?, ?, ?)";

    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userMapper;

    public UserDaoDB(JdbcTemplate jdbcTemplate, RowMapper<User> userMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> getUser(long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_USER_BY_ID, userMapper, id);
    }

    @Override
    public Optional<User> getUser(String email) {
        return jdbcTemplate.queryForObject(SQL_FIND_USER_BY_EMAIL, userMapper, email);
    }

    @Override
    public void add(User user) throws DaoException {
        jdbcTemplate.update(SQL_INSERT_INTO_USER, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.isMailing(), user.getIcon());
    }
}
