package com.epam.preproduction.kaliuha.controller.servlet;

import com.epam.preproduction.kaliuha.captcha.service.strategy.CaptchaService;
import com.epam.preproduction.kaliuha.constant.WebContextKey;
import com.epam.preproduction.kaliuha.util.ContextAttributeExtractorUtil;
import com.github.cage.GCage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {

    private static final String IMAGE = "image/";

    private GCage captchaCreator;
    private CaptchaService captchaService;

    @Override
    public void init(ServletConfig servletConfig) {
        ServletContext context = servletConfig.getServletContext();
        captchaService =  ContextAttributeExtractorUtil.getAttribute(context, WebContextKey.CAPTCHA_SERVICE);
        captchaCreator = ContextAttributeExtractorUtil.getAttribute(context, WebContextKey.CAPTCHA_CREATOR);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(IMAGE + captchaCreator.getFormat());
        String token = captchaService.get(req);
        captchaCreator.draw(token, resp.getOutputStream());
    }
}
