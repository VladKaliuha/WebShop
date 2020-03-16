package com.epam.preproduction.kaliuha.controller.servlet;

import com.epam.preproduction.kaliuha.constant.JspPage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(LogoutServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        LOG.info("New session was crated");
        if (Objects.nonNull(session)) {
            session.invalidate();
        }
        LOG.info("Customer has been logout");
        request.getRequestDispatcher(JspPage.HOME_PAGE_SERVLET).forward(request, response);
    }
}
