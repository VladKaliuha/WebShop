package com.epam.preproduction.kaliuha.filter.localization.filter;

import com.epam.preproduction.kaliuha.constant.WebContextKey;
import com.epam.preproduction.kaliuha.filter.localization.requestWrapper.LocaleRequestWrapper;
import com.epam.preproduction.kaliuha.filter.localization.strategy.LocaleStorageStrategy;
import com.epam.preproduction.kaliuha.filter.localization.strategy.container.LocaleStorageStrategyContainer;
import com.epam.preproduction.kaliuha.util.ContextAttributeExtractorUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class LocalizationFilterTest {

    private static final String SPLITTER = " ";

    private Locale defaultLocale;
    private Locale locale;

    @Mock
    private Map<String, Locale> supportedLocales;
    @Mock
    private Enumeration<Locale> requestLocales;
    @Mock
    private LocaleStorageStrategy localeStorage;
    @Mock
    private ServletContext servletContext;
    @Mock
    private HttpServletRequest request;
    @Mock
    private LocaleRequestWrapper requestWrapper;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterConfig filterConfig;
    @Mock
    private LocaleStorageStrategyContainer strategyContainer;
    @Mock
    private FilterChain chain;

    @InjectMocks
    private LocalizationFilter filter;

    @Before
    public void setUp() throws IOException, ServletException {
        createLocales();
        Mockito.when(filterConfig.getServletContext()).thenReturn(servletContext);
        Mockito.when(ContextAttributeExtractorUtil.getAttribute(servletContext, WebContextKey.LOCALE_STRATEGY_CONTAINER)).thenReturn(strategyContainer);
        Mockito.when(strategyContainer.getStrategy(Mockito.anyString())).thenReturn(localeStorage);
        Mockito.when(localeStorage.getLocale(request)).thenReturn(Optional.of(locale));
        Mockito.when(request.getLocales()).thenReturn(requestLocales);
        Mockito.doNothing().when(chain).doFilter(request, response);

        filter.init(filterConfig);
    }

    private void createLocales() {
        defaultLocale = new Locale("en");
        locale = new Locale("ru");
        Mockito.when(servletContext.getInitParameter(WebContextKey.SUPPORTED_LOCALES)).thenReturn("en ru");
        Mockito.when(servletContext.getInitParameter(WebContextKey.DEFAULT_LOCALE)).thenReturn("en");
    }

    @Test
    public void shouldSetLocaleByRequest() throws ServletException, IOException {
        Mockito.when(request.getParameter(WebContextKey.LANGUAGE)).thenReturn("ru");
        Mockito.when(supportedLocales.get(Mockito.anyString())).thenReturn(locale);
        filter.doFilter(request, response, chain);

        Mockito.verify(localeStorage).setLocale(request, response, locale);
        Mockito.verify(chain).doFilter(Mockito.anyObject(), Mockito.anyObject());
    }

    @Test
    public void shouldSetLocaleFromStorage() throws ServletException, IOException {
        Mockito.when(supportedLocales.get(Mockito.anyString())).thenReturn(null);
        Mockito.when(localeStorage.getLocale(request)).thenReturn(Optional.of(locale));
        filter.doFilter(request, response, chain);

        Mockito.verify(localeStorage).setLocale(request, response, locale);
        Mockito.verify(chain).doFilter(Mockito.anyObject(), Mockito.anyObject());
    }

    @Test
    public void shouldSetApplicationLocale() throws ServletException, IOException {
        Mockito.when(supportedLocales.get(Mockito.anyString())).thenReturn(null);
        Mockito.when(localeStorage.getLocale(request)).thenReturn(Optional.empty());
        Mockito.when(requestLocales.hasMoreElements()).thenReturn(true);
        Mockito.when(supportedLocales.containsValue(locale)).thenReturn(true);
        Mockito.when(requestLocales.nextElement()).thenReturn(locale);
        filter.doFilter(request, response, chain);

        Mockito.verify(localeStorage).setLocale(request, response, locale);
        Mockito.verify(chain).doFilter(Mockito.anyObject(), Mockito.anyObject());
    }

    @Test
    public void shouldSetDefaultLocale() throws ServletException, IOException {
        Mockito.when(supportedLocales.get(Mockito.anyString())).thenReturn(null);
        Mockito.when(localeStorage.getLocale(request)).thenReturn(Optional.empty());
        Mockito.when(requestLocales.hasMoreElements()).thenReturn(false);
        filter.doFilter(request, response, chain);

        Mockito.verify(localeStorage).setLocale(request, response, defaultLocale);
        Mockito.verify(chain).doFilter(Mockito.anyObject(), Mockito.anyObject());
    }
}