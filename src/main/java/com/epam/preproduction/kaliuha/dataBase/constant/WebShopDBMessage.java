package com.epam.preproduction.kaliuha.dataBase.constant;

public final class WebShopDBMessage {

    private WebShopDBMessage() {
        throw new UnsupportedOperationException();
    }

    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain data source";
    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain connection with db";
    public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close connection with db";
    public static final String ERR_TRANSACTION_FAIL = "Cannot rollback transaction";
    public static final String TRANSACTION_SUCCESSFUL = "Transaction is successful";

    public static final Object TRANSACTION_START = "Transaction has been started";
    public static final String ERR_CANNOT_OBTAIN_USER = "Cannot obtain user by id";
    public static final String ERR_CANNOT_INSERT_USER = "Cannot insert new user into data base";

}
