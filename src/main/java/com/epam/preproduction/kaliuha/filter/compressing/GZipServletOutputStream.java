package com.epam.preproduction.kaliuha.filter.compressing;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class GZipServletOutputStream extends ServletOutputStream {

    private GZIPOutputStream gzipOutputStream;

    public GZipServletOutputStream(OutputStream output) throws IOException {
        super();
        gzipOutputStream = new GZIPOutputStream(output);
    }

    @Override
    public void close() throws IOException {
        gzipOutputStream.close();
    }

    @Override
    public void flush() throws IOException {
        gzipOutputStream.flush();
    }

    @Override
    public void write(byte[] b) throws IOException {
        gzipOutputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        gzipOutputStream.write(b, off, len);
    }

    @Override
    public void write(int b) throws IOException {
        gzipOutputStream.write(b);
    }
}
