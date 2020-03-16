package com.epam.preproduction.kaliuha.captcha.factory.impl;

import com.epam.preproduction.kaliuha.captcha.factory.CaptchaTokenFactory;
import org.apache.commons.lang3.RandomStringUtils;

public class CaptchaTokenFactoryImpl implements CaptchaTokenFactory {

    @Override
    public String generateToken(int symbolsCount) {
        return RandomStringUtils.randomNumeric(symbolsCount);
    }
}
