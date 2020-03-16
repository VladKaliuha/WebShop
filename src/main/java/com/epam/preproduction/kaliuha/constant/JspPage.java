package com.epam.preproduction.kaliuha.constant;

public final class JspPage {

    private JspPage() {
        throw new UnsupportedOperationException();
    }

    public static final String SIGN_UP_JSP = "jsp/signUp.jsp";
    public static final String SIGN_IN_JSP = "jsp/signIn.jsp";
    public static final String SHOPPING_PAGE = "jsp/shopping.jsp";
    public static final String CART = "jsp/cart.jsp";
    public static final String HOME_PAGE_SERVLET = "/";

    public static final String CART_ACTION_PLUS = "p";
    public static final String CART_ACTION_MINUS = "m";
    public static final String CART_ACTION_DELETE = "d";
}
