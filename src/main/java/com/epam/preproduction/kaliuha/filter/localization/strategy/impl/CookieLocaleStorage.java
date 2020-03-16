package com.epam.preproduction.kaliuha.filter.localization.strategy.impl;

import com.epam.preproduction.kaliuha.constant.WebContextKey;
import com.epam.preproduction.kaliuha.filter.localization.strategy.LocaleStorageStrategy;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public class CookieLocaleStorage implements LocaleStorageStrategy {

    private static final Logger LOG = Logger.getLogger(CookieLocaleStorage.class);

    @Override
    public Optional<Locale> getLocale(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (Objects.nonNull(cookies)) {
            return Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(WebContextKey.LOCALE))
                    .findFirst()
                    .map(cookie -> Locale.forLanguageTag(cookie.getValue()));
        }
        return Optional.empty();
    }

    @Override
    public void setLocale(ServletRequest request, ServletResponse response, Locale locale) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Cookie localeCookie = new Cookie(WebContextKey.LOCALE, locale.toLanguageTag());
        ServletContext servletContext = request.getServletContext();
        int maxAge = Integer.parseInt(
                servletContext.getInitParameter("localeCookieMaxAge")
        );
        localeCookie.setMaxAge(maxAge);
        httpServletResponse.addCookie(localeCookie);
        LOG.info("Set locale to cookie " + localeCookie);
    }
}
