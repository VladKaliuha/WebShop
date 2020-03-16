package com.epam.preproduction.kaliuha.filter.cache;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CacheFilter implements Filter {

    private final static Logger LOG = Logger.getLogger(CacheFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOG.info("Initialization");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        LOG.info("Setting header");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {
        LOG.info("Destroy");
    }
}
