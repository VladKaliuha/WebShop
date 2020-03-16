package com.epam.preproduction.kaliuha.filter.compressing;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class CompressingFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(CompressingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOG.info("Initialization");
    }

    @Override
    public void destroy() {
        LOG.info("Destroy");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (acceptGZipEncoding(httpRequest) && checkContentType(httpRequest)) {
            httpResponse.addHeader("Content-Encoding", "gzip");
            GZipServletResponseWrapper gzipResponse = new GZipServletResponseWrapper(httpResponse);
            chain.doFilter(request, gzipResponse);
            gzipResponse.close();
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean checkContentType(HttpServletRequest request) {
        String contentType = request.getHeader("accept");
        LOG.info("Content type " + contentType);
        return contentType.contains("text");
    }

    private boolean acceptGZipEncoding(HttpServletRequest request) {
        String acceptEncoding = request.getHeader("Accept-Encoding");
        return Objects.nonNull(acceptEncoding) && acceptEncoding.contains("gzip");
    }
}
