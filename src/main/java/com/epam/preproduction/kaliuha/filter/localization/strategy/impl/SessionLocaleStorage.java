package com.epam.preproduction.kaliuha.filter.localization.strategy.impl;

import com.epam.preproduction.kaliuha.constant.WebContextKey;
import com.epam.preproduction.kaliuha.filter.localization.strategy.LocaleStorageStrategy;
import org.apache.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Optional;

public class SessionLocaleStorage implements LocaleStorageStrategy {

    private static final Logger LOG = Logger.getLogger(CookieLocaleStorage.class);

    @Override
    public Optional<Locale> getLocale(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        String languageTag = String.valueOf(
                session.getAttribute(WebContextKey.LOCALE)
        );
        return Optional.ofNullable(Locale.forLanguageTag(languageTag));
    }

    @Override
    public void setLocale(ServletRequest request, ServletResponse response, Locale locale) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(WebContextKey.LOCALE, locale.toLanguageTag());
        LOG.info("Set locale to session " + locale);
    }
}
