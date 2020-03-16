package com.epam.preproduction.kaliuha.filter.localization.strategy;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Locale;
import java.util.Optional;

public interface LocaleStorageStrategy {

    Optional<Locale> getLocale(ServletRequest request);

    void setLocale(ServletRequest request, ServletResponse response, Locale locale);
}
