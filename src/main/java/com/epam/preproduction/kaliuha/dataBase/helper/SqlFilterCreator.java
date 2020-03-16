package com.epam.preproduction.kaliuha.dataBase.helper;

import com.epam.preproduction.kaliuha.entity.bean.ProductFilterBean;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;

public class SqlFilterCreator {

    private static final String PRODUCT_NAME_FILTER = "product.name LIKE ";
    private static final String FABRICATOR_NAME_FILTER = "fabricator.name LIKE ";
    private static final String CATEGORY_NAME_FILTER = "category.name LIKE ";
    private static final String MIN_PRICE_FILTER = "price > ";
    private static final String MAX_PRICE_FILTER = "price < ";
    private static final String AND = " AND ";
    private static final String QUOTE = "'";
    private static final String OR = " OR ";

    private StringBuilder filterQuery;
    private SqlQueryCreator queryCreator;

    public SqlFilterCreator() {
        queryCreator = new SqlQueryCreator();
        filterQuery = new StringBuilder();
    }

    public String getFilterQuery(ProductFilterBean filterBean) {
        return productName(filterBean.getProductName()).and()
                .category(filterBean.getProductCategory()).and()
                .fabricator(filterBean.getProductFabricator()).and()
                .minPrice(filterBean.getMinPrice()).and()
                .maxPrice(filterBean.getMaxPrice()).getResult();
    }

    private SqlFilterCreator productName(String name) {
        filterQuery.append(addFilter(PRODUCT_NAME_FILTER, name));
        return this;
    }

    public SqlFilterCreator category(String[] categories) {
        if (Objects.nonNull(categories)) {
            Arrays.stream(categories).forEach(category -> filterQuery.append(addFilter(CATEGORY_NAME_FILTER, category)).append(OR));
            trimExtraSymbols(OR);
        }
        return this;
    }

    public SqlFilterCreator fabricator(String[] fabricators) {
        if (Objects.nonNull(fabricators)) {
            Arrays.stream(fabricators).forEach(fabricator -> filterQuery.append(addFilter(FABRICATOR_NAME_FILTER, fabricator)).append(OR));
            trimExtraSymbols(OR);
        }
        return this;
    }

    private SqlFilterCreator minPrice(String price) {
        filterQuery.append(addFilter(MIN_PRICE_FILTER, price));
        return this;
    }

    private SqlFilterCreator maxPrice(String price) {
        filterQuery.append(addFilter(MAX_PRICE_FILTER, price));
        return this;
    }

    private SqlFilterCreator and() {
        if (!filterQuery.toString().endsWith(AND) && filterQuery.length() != 0) {
            filterQuery.append(AND);
        }
        return this;
    }

    private String getResult() {
        if (filterQuery.toString().endsWith(AND)) {
            trimExtraSymbols(AND);
        }
        if (hasFilters()) {
            queryCreator.where().append(filterQuery.toString());
        }
        return queryCreator.getFinalQuery();
    }

    private void trimExtraSymbols(String extraSymbols) {
        int lastIndex = filterQuery.lastIndexOf(extraSymbols);
        if (lastIndex != -1) {
            filterQuery.replace(lastIndex, lastIndex + extraSymbols.length(), "");
        }
    }


    private boolean hasFilters() {
        return StringUtils.isNotBlank(filterQuery);
    }

    private String addFilter(String filter, String parameter) {
        if (StringUtils.isNotBlank(parameter)) {
            return filter + QUOTE + parameter + QUOTE;
        }
        return StringUtils.EMPTY;
    }
}
