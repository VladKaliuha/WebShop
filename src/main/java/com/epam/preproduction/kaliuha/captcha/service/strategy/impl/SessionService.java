package com.epam.preproduction.kaliuha.captcha.service.strategy.impl;

import com.epam.preproduction.kaliuha.captcha.service.strategy.CaptchaService;
import com.epam.preproduction.kaliuha.captcha.storage.CaptchaStorage;
import com.epam.preproduction.kaliuha.constant.WebContextKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionService implements CaptchaService {

    private CaptchaStorage captchaStorage;

    public SessionService(CaptchaStorage captchaStorage) {
        this.captchaStorage = captchaStorage;
    }

    @Override
    public void put(HttpServletRequest request, HttpServletResponse response, String token) {
        HttpSession session = request.getSession();
        long id = createId();
        captchaStorage.add(id, token);
        session.setAttribute(WebContextKey.TOKEN_ID, id);
    }

    @Override
    public String get(HttpServletRequest request) {
        long id = (long) request.getSession().getAttribute(WebContextKey.TOKEN_ID);
        return captchaStorage.get(id);
    }
}
