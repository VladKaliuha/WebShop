package com.epam.preproduction.kaliuha.captcha.service.strategy.impl;

import com.epam.preproduction.kaliuha.captcha.service.strategy.CaptchaService;
import com.epam.preproduction.kaliuha.captcha.storage.CaptchaStorage;
import com.epam.preproduction.kaliuha.constant.WebContextKey;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class CookieService implements CaptchaService {

    private CaptchaStorage captchaStorage;

    public CookieService(CaptchaStorage captchaStorage) {
        this.captchaStorage = captchaStorage;
    }

    @Override
    public void put(HttpServletRequest request, HttpServletResponse response, String token) {
        long id = createId();
        Cookie captchaCookie = new Cookie(WebContextKey.COOKIE_TOKEN_ID, String.valueOf(id));
        captchaStorage.add(id, token);
        response.addCookie(captchaCookie);
    }

    @Override
    public String get(HttpServletRequest request) {
        long id = getIdFromCookie(request);
        return captchaStorage.get(id);
    }

    private long getIdFromCookie(HttpServletRequest request) {
        return Long.parseLong(Arrays.stream(request.getCookies())
                .filter(cookie -> WebContextKey.COOKIE_TOKEN_ID.equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(StringUtils.EMPTY));
    }
}
