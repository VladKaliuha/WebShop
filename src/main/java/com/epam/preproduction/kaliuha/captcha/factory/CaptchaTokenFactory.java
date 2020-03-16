package com.epam.preproduction.kaliuha.captcha.factory;

public interface CaptchaTokenFactory {

    String generateToken(int symbolsCount);
}
