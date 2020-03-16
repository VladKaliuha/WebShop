package com.epam.preproduction.kaliuha.captcha.storage.cleaner;

import com.epam.preproduction.kaliuha.captcha.storage.CaptchaStorage;
import com.epam.preproduction.kaliuha.constant.ServletErrorMessage;
import com.epam.preproduction.kaliuha.constant.WebContextKey;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import java.util.concurrent.TimeUnit;

public class StorageCleaner extends Thread {

    private static final int DURATION = 5;
    private ServletContext servletContext;
    private long timeOut;
    private static final Logger LOG = Logger.getLogger(StorageCleaner.class);

    public StorageCleaner(ServletContext servletContext) {
        this.servletContext = servletContext;
        this.timeOut = Long.parseLong(servletContext.getInitParameter(WebContextKey.CAPTCHA_TIMEOUT));
    }

    @Override
    public void run() {
        CaptchaStorage captchaStorage = (CaptchaStorage) servletContext.getAttribute(WebContextKey.CAPTCHA_STORAGE);
        while (!interrupted()) {
            try {
                sleep(TimeUnit.SECONDS.toMillis(DURATION));
            } catch (InterruptedException e) {
                LOG.warn(ServletErrorMessage.EXCEPTION + e);
                this.start();
            }
            clearStorageByTimeOut(captchaStorage);
        }
    }

    private void clearStorageByTimeOut(CaptchaStorage captchaStorage) {
        captchaStorage.getStorage().keySet().removeIf(id -> (System.currentTimeMillis() - id) > timeOut);
    }
}

