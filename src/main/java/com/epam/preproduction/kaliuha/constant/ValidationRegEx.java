package com.epam.preproduction.kaliuha.constant;

public final class ValidationRegEx {

    private ValidationRegEx(){
        throw new UnsupportedOperationException();
    }

    public static final String EMAIL = "^([_a-zA-Z0-9-]+)(\\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+\\.)+([a-zA-Z]{2,3})$";
    public static final String NAME = "^[A-Za-zА-Яа-яё]+$";
    public static final String PASSWORD = "^[a-zA-Z0-9]{6,}$";
}
