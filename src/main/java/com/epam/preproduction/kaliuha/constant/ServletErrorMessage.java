package com.epam.preproduction.kaliuha.constant;

public final class ServletErrorMessage {

    private ServletErrorMessage() {
        throw new UnsupportedOperationException();
    }

    public static final String INVALID_EMAIL = "Email can contains a-zA-Z0-9 and symbols. Should contains @";
    public static final String INVALID_NAME = "Name/Surname should contains only letters";
    public static final String INVALID_PASSWORD = "Password can contains a-zA-Z0-9. Min 6ch";
    public static final String DIFFERENT_PASSWORDS = "Different passwords";
    public static final String MAIL_ALREADY_EXIST = "This email is already in use";
    public static final String INVALID_CAPTCHA = "Captcha doesn't match to the image";
    public static final String TIMEOUT = "Sorry, time out, registration has been stopped";
    public static final String EXCEPTION = "Exception";
    public static final String INVALID_LOGIN_DATA = "Invalid login/password";

}
