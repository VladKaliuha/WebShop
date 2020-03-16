package com.epam.preproduction.kaliuha.captcha.service.strategy.impl;

import com.epam.preproduction.kaliuha.captcha.service.strategy.CaptchaService;
import com.epam.preproduction.kaliuha.captcha.storage.CaptchaStorage;
import com.epam.preproduction.kaliuha.constant.WebContextKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HiddenFieldService implements CaptchaService {

    private CaptchaStorage captchaStorage;

    public HiddenFieldService(CaptchaStorage captchaStorage) {
        this.captchaStorage = captchaStorage;
    }

    @Override
    public void put(HttpServletRequest request, HttpServletResponse response, String token) {
        long id = createId();
        captchaStorage.add(id, token);
        request.setAttribute(WebContextKey.TOKEN_ID, id);
    }

    @Override
    public String get(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter(WebContextKey.TOKEN_ID));
        return captchaStorage.get(id);
    }
}
