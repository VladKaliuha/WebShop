package com.epam.preproduction.kaliuha.controller.servlet;

import com.epam.preproduction.kaliuha.captcha.factory.CaptchaTokenFactory;
import com.epam.preproduction.kaliuha.captcha.service.strategy.CaptchaService;
import com.epam.preproduction.kaliuha.constant.JspPage;
import com.epam.preproduction.kaliuha.constant.WebContextKey;
import com.epam.preproduction.kaliuha.controller.facade.CustomerFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SignUpServletTest {

    private static final int TOKEN_SYMBOLS_COUNT = 6;
    private static final String T_CAPTCHA_TEXT = "123456";

    @Mock
    private CaptchaService captchaService;
    @Mock
    private CaptchaTokenFactory tokenFactory;
    @Mock
    private CustomerFacade customerFacade;
    @Mock
    private ServletContext context;
    @Mock
    private ServletConfig config;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private List<String> errorMessages;

    @InjectMocks
    private SignUpServlet servlet;

    @Before
    public void setUp() throws ServletException, IOException {
        Mockito.when(config.getServletContext()).thenReturn(context);
        Mockito.when(context.getAttribute(WebContextKey.CAPTCHA_SERVICE)).thenReturn(captchaService);
        Mockito.when(context.getAttribute(WebContextKey.TOKEN_FACTORY)).thenReturn(tokenFactory);
        Mockito.when(context.getAttribute(WebContextKey.CUSTOMER_FACADE)).thenReturn(customerFacade);

        Mockito.when(tokenFactory.generateToken(TOKEN_SYMBOLS_COUNT)).thenReturn(T_CAPTCHA_TEXT);
        Mockito.when(customerFacade.registration(Mockito.any(), Mockito.any())).thenReturn(errorMessages);
        Mockito.doNothing().when(captchaService).put(request, response, T_CAPTCHA_TEXT);

        Mockito.when(request.getRequestDispatcher(JspPage.SIGN_UP_JSP)).thenReturn(dispatcher);
        Mockito.doNothing().when(dispatcher).forward(Mockito.any(), Mockito.any());
        Mockito.doNothing().when(response).sendRedirect(JspPage.HOME_PAGE_SERVLET);
    }

    @Test
    public void shouldForwardToCorrectPageWhenDoGetMethodIsCalled() throws ServletException, IOException {
        servlet.init(config);
        servlet.doGet(request, response);
        Mockito.verify(dispatcher).forward(request, response);
    }

    @Test
    public void shouldCreateNewUserInSystem() throws ServletException, IOException {
        Mockito.when(errorMessages.isEmpty()).thenReturn(true);
        servlet.init(config);
        servlet.doPost(request, response);
        Mockito.verify(response).sendRedirect(JspPage.HOME_PAGE_SERVLET);
    }

    @Test
    public void shouldForwardBackToSignUpPageWithErrorList() throws ServletException, IOException {
        Mockito.when(errorMessages.isEmpty()).thenReturn(false);
        servlet.init(config);
        servlet.doPost(request, response);
        Mockito.verify(dispatcher).forward(request, response);
    }
}

