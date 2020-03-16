package com.epam.preproduction.kaliuha.util;

import javax.servlet.ServletContext;

public final class ContextAttributeExtractorUtil {

    private ContextAttributeExtractorUtil() {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getAttribute(final ServletContext context, final String key) {
        return (T) context.getAttribute(key);
    }
}
