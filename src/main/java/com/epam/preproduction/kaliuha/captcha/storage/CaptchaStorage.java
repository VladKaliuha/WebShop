package com.epam.preproduction.kaliuha.captcha.storage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CaptchaStorage {

    private Map<Long, String> storage;

    public CaptchaStorage() {
        storage = Collections.synchronizedMap(new HashMap<>());
    }

    public Map<Long, String> getStorage() {
        return storage;
    }

    public void add(Long id, String token) {
        storage.put(id, token);
    }

    public String get(Long id) {
        return storage.get(id);
    }
}
