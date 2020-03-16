package com.epam.preproduction.kaliuha.filter.localization.filter;

import com.epam.preproduction.kaliuha.constant.WebContextKey;
import com.epam.preproduction.kaliuha.filter.localization.requestWrapper.LocaleRequestWrapper;
import com.epam.preproduction.kaliuha.filter.localization.strategy.LocaleStorageStrategy;
import com.epam.preproduction.kaliuha.filter.localization.strategy.container.LocaleStorageStrategyContainer;
import com.epam.preproduction.kaliuha.util.ContextAttributeExtractorUtil;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class LocalizationFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(LocalizationFilter.class);
    private static final String SPLITTER = " ";

    private Locale defaultLocale;
    private Map<String, Locale> supportedLocales;
    private LocaleStorageStrategy localeStorage;

    @Override
    public void init(FilterConfig filterConfig) {
        LOG.info("LocalizationFilter initialization");
        ServletContext servletContext = filterConfig.getServletContext();
        localeStorage = chooseLocaleStorageStrategy(servletContext);
        defaultLocale = getDefaultLocale(servletContext);
        supportedLocales = getSupportedLocales(servletContext);
        logInitializedParameters();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Locale currentLocale = resolveLocale(request);
        localeStorage.setLocale(request, response, currentLocale);
        response.setLocale(currentLocale);
        LocaleRequestWrapper requestWrapper = new LocaleRequestWrapper((HttpServletRequest) request, supportedLocales, currentLocale);
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {
        LOG.info("Destroy");
    }

    private Locale resolveLocale(ServletRequest request) {
        return getUserLocale(request)
                .orElse(getLocaleFromStorage(request)
                        .orElse(getApplicationLocale(request)));
    }

    private Locale getApplicationLocale(ServletRequest request) {
        Enumeration<Locale> requestLocales = request.getLocales();
        while (requestLocales.hasMoreElements()) {
            Locale locale = requestLocales.nextElement();
            if (supportedLocales.containsValue(locale)) {
                return locale;
            }
        }
        return defaultLocale;
    }

    private LocaleStorageStrategy chooseLocaleStorageStrategy(ServletContext servletContext) {
        String strategy = String.valueOf(servletContext.getInitParameter(WebContextKey.LOCALE_STRATEGY));
        LocaleStorageStrategyContainer strategyContainer = ContextAttributeExtractorUtil.getAttribute(servletContext, WebContextKey.LOCALE_STRATEGY_CONTAINER);
        return strategyContainer.getStrategy(strategy);
    }

    private Optional<Locale> getLocaleFromStorage(ServletRequest request) {
        return localeStorage.getLocale(request);
    }

    private Optional<Locale> getUserLocale(ServletRequest request) {
        String userLocale = request.getParameter(WebContextKey.LANGUAGE);
        return Optional.ofNullable(supportedLocales.get(userLocale));
    }

    private Locale getDefaultLocale(ServletContext servletContext) {
        return new Locale(servletContext.getInitParameter(WebContextKey.DEFAULT_LOCALE));
    }

    private Map<String, Locale> getSupportedLocales(ServletContext servletContext) {
        return Arrays.stream(servletContext.getInitParameter(WebContextKey.SUPPORTED_LOCALES).split(SPLITTER))
                .collect(Collectors.toMap(lang -> lang, Locale::new));
    }

    private void logInitializedParameters() {
        LOG.info("Locale storage --> " + localeStorage);
        LOG.info("Default locale --> " + defaultLocale);
        LOG.info("Supported locales --> " + supportedLocales);
    }
}
