package com.epam.preproduction.kaliuha.controller.servlet;

import com.epam.preproduction.kaliuha.captcha.factory.CaptchaTokenFactory;
import com.epam.preproduction.kaliuha.captcha.service.strategy.CaptchaService;
import com.epam.preproduction.kaliuha.constant.JspPage;
import com.epam.preproduction.kaliuha.constant.WebContextKey;
import com.epam.preproduction.kaliuha.constant.WebShopEntityField;
import com.epam.preproduction.kaliuha.controller.facade.CustomerFacade;
import com.epam.preproduction.kaliuha.controller.helper.ServletHelper;
import com.epam.preproduction.kaliuha.entity.bean.RegistrationBean;
import com.epam.preproduction.kaliuha.util.ContextAttributeExtractorUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/sign-up")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class SignUpServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(SignInServlet.class);

    private static final int SYMBOLS_COUNT = 6;
    private static final String ICON = "icon";
    private CaptchaService captchaService;
    private CaptchaTokenFactory tokenFactory;
    private CustomerFacade customerFacade;

    @Override
    public void init(ServletConfig servletConfig) {
        ServletContext context = servletConfig.getServletContext();

        captchaService = ContextAttributeExtractorUtil.getAttribute(context, WebContextKey.CAPTCHA_SERVICE);
        tokenFactory = ContextAttributeExtractorUtil.getAttribute(context, WebContextKey.TOKEN_FACTORY);
        customerFacade = ContextAttributeExtractorUtil.getAttribute(context, WebContextKey.CUSTOMER_FACADE);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegistrationBean bean = createRegistrationBean(request);
        List<String> errorMessages = customerFacade.registration(bean, request.getPart(ICON));
        if (errorMessages.isEmpty()) {
            LOG.info("Customer has been signed up");
            response.sendRedirect(JspPage.HOME_PAGE_SERVLET);
        } else {
            LOG.info("Signing up is failed");
            ServletHelper.prepareRequestToSaveRegistrationData(request, bean, errorMessages);
            doGet(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = tokenFactory.generateToken(SYMBOLS_COUNT);
        captchaService.put(req, resp, token);
        RequestDispatcher dispatcher = req.getRequestDispatcher(JspPage.SIGN_UP_JSP);
        dispatcher.forward(req, resp);
    }

    private RegistrationBean createRegistrationBean(HttpServletRequest request) {
        String firstName = request.getParameter(WebShopEntityField.FIRST_NAME);
        String lastName = request.getParameter(WebShopEntityField.LAST_NAME);
        String email = request.getParameter(WebShopEntityField.EMAIL);
        String password = request.getParameter(WebShopEntityField.CUSTOMER_PASSWORD);
        String confirmPassword = request.getParameter(WebShopEntityField.CPASSWORD);
        String captcha = request.getParameter(WebShopEntityField.CAPTCHA);
        String token = captchaService.get(request);
        boolean mailing = Boolean.parseBoolean(request.getParameter(WebShopEntityField.MAILING));
        return new RegistrationBean(firstName, lastName, email, password, confirmPassword, captcha, mailing, token);
    }
}
