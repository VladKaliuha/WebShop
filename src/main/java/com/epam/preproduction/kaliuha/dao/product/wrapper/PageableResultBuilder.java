package com.epam.preproduction.kaliuha.dao.product.wrapper;

import com.epam.preproduction.kaliuha.dataBase.helper.SqlQueryCreator;
import com.epam.preproduction.kaliuha.entity.bean.SortingRule;
import com.epam.preproduction.kaliuha.entity.impl.Product;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.function.Function;

public class PageableResultBuilder {

    private static final String SYMBOL_IN_UI = "-";
    private static final String SYMBOL_IN_ENUM = "_";

    private Function<String, List<Product>> jdbcTemplateFunction;
    private SqlQueryCreator sql;

    public PageableResultBuilder(String sql, Function<String, List<Product>> jdbcTemplateFunction) {
        this.sql = new SqlQueryCreator(sql);
        this.jdbcTemplateFunction = jdbcTemplateFunction;
    }

    public PageableResultBuilder skip(int count) {
        sql.limit(count);
        return this;
    }

    public PageableResultBuilder number(int count) {
        sql.number(count);
        return this;
    }

    public PageableResultBuilder sortBy(String sortRule) {
        if (StringUtils.isNotBlank(sortRule)) {
            SortingRule rule = SortingRule.valueOf(changeRuleNameForEnum(sortRule));
            sql.orderBy(rule);
        }
        return this;
    }

    public List<Product> result() {
        return jdbcTemplateFunction.apply(sql.getFinalQuery());
    }

    private String changeRuleNameForEnum(String sortingRule) {
        return sortingRule.replace(SYMBOL_IN_UI, SYMBOL_IN_ENUM).toUpperCase();
    }
}
