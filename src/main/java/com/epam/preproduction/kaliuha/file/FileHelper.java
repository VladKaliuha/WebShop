package com.epam.preproduction.kaliuha.file;

import org.apache.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class FileHelper {

    private static final Logger LOG = Logger.getLogger(FileHelper.class);

    private static final int MAX_SIZE = 8192;
    private static final String FULL_PATH = "src/main/webapp";
    private static final String NEW_FILE = "Created new file";
    private static final String SAVED_SUCCESSFULLY = "Saved into the file";

    private FileHelper() {
        throw new UnsupportedOperationException();
    }

    public static void readFileFromServlet(final Part filePart, final String file) throws IOException {
        new File(FULL_PATH + file).createNewFile();
        LOG.info(NEW_FILE);
        try (InputStream fileContent = filePart.getInputStream();
             OutputStream outputStream = new FileOutputStream(new File(FULL_PATH + file))) {
            int qt;
            byte[] icon = new byte[MAX_SIZE];
            while ((qt = fileContent.read(icon)) != -1) {
                outputStream.write(icon, 0, qt);
            }
            LOG.info(SAVED_SUCCESSFULLY);
            outputStream.flush();
        }
    }
}
