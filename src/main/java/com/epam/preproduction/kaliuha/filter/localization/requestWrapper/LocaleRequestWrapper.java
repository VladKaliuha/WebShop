package com.epam.preproduction.kaliuha.filter.localization.requestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

public class LocaleRequestWrapper extends HttpServletRequestWrapper {

    private Map<String, Locale> supportedLocales;
    private Locale currentLocale;

    public LocaleRequestWrapper(HttpServletRequest httpServletRequest, Map<String, Locale> supportedLocales, Locale currentLocale) {
        super(httpServletRequest);
        this.supportedLocales = supportedLocales;
        this.currentLocale = currentLocale;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return Collections.enumeration(supportedLocales.values());
    }

    @Override
    public Locale getLocale() {
        return currentLocale;
    }
}
