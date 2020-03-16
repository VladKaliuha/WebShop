package com.epam.preproduction.kaliuha.dataBase.helper;

import com.epam.preproduction.kaliuha.entity.bean.SortingRule;

public class SqlQueryCreator {

    private StringBuilder query;

    public SqlQueryCreator() {
        query = new StringBuilder();
    }

    public SqlQueryCreator(String sql) {
        query = new StringBuilder(sql);
    }

    public SqlQueryCreator and() {
        query.append(" AND ");
        return this;
    }

    public SqlQueryCreator or() {
        query.append(" OR ");
        return this;
    }

    public SqlQueryCreator append(String value) {
        query.append(value);
        return this;
    }

    public SqlQueryCreator from(String table) {
        query.append(" FROM ").append(table);
        return this;
    }

    public SqlQueryCreator orderBy(SortingRule rule) {
        query.append(" ORDER BY ").append(rule.getRule());
        return this;
    }

    public SqlQueryCreator innerJoin(String table) {
        query.append(" INNER JOIN ").append(table);
        return this;
    }

    public SqlQueryCreator on(String condition) {
        query.append(" ON ").append(condition);
        return this;
    }

    public SqlQueryCreator select() {
        query.append("SELECT ");
        return this;
    }

    public SqlQueryCreator values() {
        query.append(" VALUES ");
        return this;
    }

    public SqlQueryCreator where() {
        query.append(" WHERE ");
        return this;
    }

    public SqlQueryCreator limit(int count) {
        query.append(" LIMIT ").append(count);
        return this;
    }

    public SqlQueryCreator number(int count) {
        query.append(", ").append(count);
        return this;
    }

    public SqlQueryCreator as(String name) {
        query.append(" AS ").append(name);
        return this;
    }

    public String getQueryAsArgument(String query) {
        return "(" + query + ")";
    }

    public SqlQueryCreator setArguments(String... args) {
        for (int i = 0; i < args.length; i++) {
            String field = args[i];
            query.append(field);
            if (i + 1 != args.length) {
                query.append(", ");
            }
        }
        return this;
    }

    public String getFinalQuery() {
        return query.toString();
    }

    public SqlQueryCreator insertInto(String table) {
        query.append(" INSERT INTO ").append(table);
        return this;
    }
}