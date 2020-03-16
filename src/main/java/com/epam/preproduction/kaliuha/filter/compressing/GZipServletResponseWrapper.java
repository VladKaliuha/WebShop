package com.epam.preproduction.kaliuha.filter.compressing;

import org.apache.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Objects;

public class GZipServletResponseWrapper extends HttpServletResponseWrapper {

    private static final Logger LOG = Logger.getLogger(GZipServletResponseWrapper.class);

    private GZipServletOutputStream gzipOutputStream;
    private PrintWriter printWriter;

    public GZipServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public void close() throws IOException {
        closePrintWriter();
        closeGZipOutputStream();
    }

    @Override
    public void flushBuffer() {
        if (Objects.nonNull(printWriter)) {
            printWriter.flush();
        }
        try {
            super.flushBuffer();
            if (Objects.nonNull(gzipOutputStream)) {
                gzipOutputStream.flush();
            }
        } catch (IOException e) {
            LOG.info(e.getStackTrace());
        }
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (Objects.nonNull(printWriter)) {
            throw new IllegalStateException("PrintWriter obtained already - cannot get OutputStream");
        }
        if (Objects.isNull(gzipOutputStream)) {
            gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
        }
        return gzipOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (Objects.isNull(printWriter)) {
            if (Objects.nonNull(gzipOutputStream)) {
                throw new IllegalStateException("OutputStream obtained already - cannot get PrintWriter");
            }
            gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
            printWriter = new PrintWriter(new OutputStreamWriter(gzipOutputStream, getResponse().getCharacterEncoding()));
        }
        return printWriter;
    }

    private void closeGZipOutputStream() throws IOException {
        if (Objects.nonNull(gzipOutputStream)) {
            gzipOutputStream.close();
        }
    }

    private void closePrintWriter() {
        if (Objects.nonNull(printWriter)) {
            printWriter.close();
        }
    }
}
