package com.epam.preproduction.kaliuha.controller.servlet;

import com.epam.preproduction.kaliuha.constant.JspPage;
import com.epam.preproduction.kaliuha.constant.ServletErrorMessage;
import com.epam.preproduction.kaliuha.constant.WebContextKey;
import com.epam.preproduction.kaliuha.constant.WebShopEntityField;
import com.epam.preproduction.kaliuha.controller.facade.CustomerFacade;
import com.epam.preproduction.kaliuha.controller.helper.ServletHelper;
import com.epam.preproduction.kaliuha.entity.impl.User;
import com.epam.preproduction.kaliuha.util.ContextAttributeExtractorUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(SignInServlet.class);

    private CustomerFacade customerFacade;

    @Override
    public void init(ServletConfig servletConfig) {
        ServletContext context = servletConfig.getServletContext();

        customerFacade = ContextAttributeExtractorUtil.getAttribute(context, WebContextKey.CUSTOMER_FACADE);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(WebShopEntityField.EMAIL);
        String password = request.getParameter(WebShopEntityField.CUSTOMER_PASSWORD);

        Optional<User> user = customerFacade.login(email, password);
        if (user.isPresent()) {
            request.getSession().setAttribute(WebContextKey.USER, user.get());
            LOG.info("Customer has been signed in");
            response.sendRedirect(JspPage.HOME_PAGE_SERVLET);
        } else {
            ServletHelper.prepareRequestToSaveLoginData(request, email, ServletErrorMessage.INVALID_LOGIN_DATA);
            LOG.info("Signing in is failed");
            doGet(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(JspPage.SIGN_IN_JSP);
        dispatcher.forward(req, resp);
    }
}
