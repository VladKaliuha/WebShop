package com.epam.preproduction.kaliuha.dao.fabricator.impl;

import com.epam.preproduction.kaliuha.dao.fabricator.FabricatorDao;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.JdbcTemplate;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.RowMapper;
import com.epam.preproduction.kaliuha.entity.impl.Fabricator;

import java.util.List;
import java.util.Optional;

public class FabricatorDaoImpl implements FabricatorDao {

    private static final String SQL_GET_FABRICATOR_BY_ID =
            "SELECT * " +
                    "FROM fabricator \n" +
                    "WHERE id=?";
    private static final String SQL_GET_ALL_FABRICATORS =
            "SELECT * FROM fabricator";

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Fabricator> fabricatorMapper;

    public FabricatorDaoImpl(JdbcTemplate jdbcTemplate, RowMapper<Fabricator> fabricatorMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.fabricatorMapper = fabricatorMapper;
    }

    @Override
    public Optional<Fabricator> getFabricator(long id) {
        return jdbcTemplate.queryForObject(SQL_GET_FABRICATOR_BY_ID, fabricatorMapper, id);
    }

    @Override
    public List<Fabricator> getAllFabricators() {
        return jdbcTemplate.queryForList(SQL_GET_ALL_FABRICATORS, fabricatorMapper);
    }
}
