package com.epam.preproduction.kaliuha.dao.category.impl;

import com.epam.preproduction.kaliuha.dao.category.CategoryDao;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.JdbcTemplate;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.RowMapper;
import com.epam.preproduction.kaliuha.entity.impl.Category;

import java.util.List;
import java.util.Optional;

public class CategoryDaoImpl implements CategoryDao {

    private static final String SQL_GET_CATEGORY_BY_ID =
            "SELECT * " +
                    "FROM category \n" +
                    "WHERE id=?";
    private static final String SQL_GET_ALL_CATEGORIES =
            "SELECT * FROM category";

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Category> categoryMapper;

    public CategoryDaoImpl(JdbcTemplate jdbcTemplate, RowMapper<Category> categoryMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Optional<Category> getCategory(long id) {
        return jdbcTemplate.queryForObject(SQL_GET_CATEGORY_BY_ID, categoryMapper, id);
    }

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.queryForList(SQL_GET_ALL_CATEGORIES, categoryMapper);
    }
}
