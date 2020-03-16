package com.epam.preproduction.kaliuha.entity.bean;

public enum SortingRule {

    A_Z(" product.name ASC"),
    Z_A(" product.name DESC"),
    MIN_MAX(" product.price ASC"),
    MAX_MIN(" product.price DESC");

    private String rule;

    SortingRule(String rule) {
        this.rule = rule;
    }

    public String getRule() {
        return rule;
    }
}
