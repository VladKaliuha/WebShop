package com.epam.preproduction.kaliuha.constant;

public final class WebShopEntityField {

    private WebShopEntityField() {
        throw new UnsupportedOperationException();
    }

    public static final String ID = "id";
    public static final String NAME = "name";

    //User
    public static final String EMAIL = "email";
    public static final String CUSTOMER_PASSWORD = "password";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String CPASSWORD = "cpassword";
    public static final String MAILING = "mailing";
    public static final String CAPTCHA = "captcha";
    public static final String ICON = "icon";

    //Product
    public static final String PRODUCT_NAME_JSP_PARAM = "product_name";
    public static final String FABRICATOR_JSP_PARAM = "product_fabricator";
    public static final String MIN_PRICE_JSP_PARAM = "min_price";
    public static final String MAX_PRICE_JSP_PARAM = "max_price";
    public static final String CATEGORY_JSP_PARAM = "product_category";
    public static final String FABRICATOR = "fabricator";
    public static final String PRICE = "price";
    public static final String INFO = "info";
    public static final String CATEGORY = "category";
    public static final String ITEMS_COUNT_JSP_PARAM = "items_count";
    public static final String PAGE_JSP_PARAM = "page";
    public static final String SORTING_JSP_PARAM = "sorting";


}
