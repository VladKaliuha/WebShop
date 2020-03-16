package com.epam.preproduction.kaliuha.captcha.service.strategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CaptchaService {

    void put(HttpServletRequest request, HttpServletResponse response, String token);

    String get(HttpServletRequest request);

    default long createId() {
        return System.currentTimeMillis();
    }
}
