package com.epam.preproduction.kaliuha.dao.product.impl;

import com.epam.preproduction.kaliuha.dao.product.ProductDao;
import com.epam.preproduction.kaliuha.dao.product.wrapper.PageableResultBuilder;
import com.epam.preproduction.kaliuha.dataBase.exception.DaoException;
import com.epam.preproduction.kaliuha.dataBase.helper.SqlFilterCreator;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.JdbcTemplate;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.RowMapper;
import com.epam.preproduction.kaliuha.entity.bean.ProductFilterBean;
import com.epam.preproduction.kaliuha.entity.impl.Product;
import org.apache.log4j.Logger;

import java.util.Optional;

public class ProductDaoDB implements ProductDao {

    private static final Logger LOG = Logger.getLogger(ProductDaoDB.class);

    private static final String SQL_GET_PRODUCT_BY_ID =
            "SELECT product.*, category.name as category, fabricator.name as fabricator " +
                    "FROM product \n" +
                    "left join fabricator on fabricator.id = product.fabricator_id \n" +
                    "left join category on product.category_id=category.id \n" +
                    "WHERE product.id=?";
    private static final String SQL_INSERT_INTO_PRODUCT =
            "INSERT INTO webshopdb.product(name, category_id, fabricator_id, price, info, icon)\n" +
                    "VALUES (?, " +
                    "(SELECT id FROM category WHERE name LIKE ?), \n" +
                    "(SELECT id FROM fabricator WHERE name LIKE ?), " +
                    "?, ?, ?)";
    private static final String SQL_GET_ALL_PRODUCTS =
            "SELECT product.*, category.name as category, fabricator.name as fabricator\n" +
                    "FROM product \n" +
                    "left join fabricator on fabricator.id = product.fabricator_id \n" +
                    "left join category on product.category_id=category.id ";
    private static final String SQL_GET_PRODUCTS_COUNT =
            "SELECT count(product.id) as count\n" +
                    "FROM product \n" +
                    "left join fabricator on fabricator.id = product.fabricator_id \n" +
                    "left join category on product.category_id=category.id ";

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Product> productMapper;

    public ProductDaoDB(JdbcTemplate jdbcTemplate, RowMapper<Product> productMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.productMapper = productMapper;
    }

    @Override
    public PageableResultBuilder getAllProducts(ProductFilterBean filterBean) {
        SqlFilterCreator productFilter = new SqlFilterCreator();
        String filtersQuery = productFilter.getFilterQuery(filterBean);
        return new PageableResultBuilder(SQL_GET_ALL_PRODUCTS + filtersQuery, sql -> jdbcTemplate.queryForList(sql, productMapper));
    }

    @Override
    public Optional<Product> getProduct(long id) {
        return jdbcTemplate.queryForObject(SQL_GET_PRODUCT_BY_ID, productMapper, id);
    }

    @Override
    public Long getProductsCount(ProductFilterBean filterBean) {
        SqlFilterCreator creator = new SqlFilterCreator();
        String countFilteredProductsQuery = creator.getFilterQuery(filterBean);
        return jdbcTemplate.execute(SQL_GET_PRODUCTS_COUNT + countFilteredProductsQuery);
    }

    @Override
    public void addProduct(Product product) throws DaoException {
        jdbcTemplate.update(SQL_INSERT_INTO_PRODUCT, product.getName(), product.getCategory(), product.getFabricator(), product.getPrice(), product.getInfo(), product.getIcon());
    }
}
