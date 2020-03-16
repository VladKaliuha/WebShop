package com.epam.preproduction.kaliuha.repository;

import com.epam.preproduction.kaliuha.entity.impl.User;

import java.util.HashMap;
import java.util.Map;

public class Repository {

    private Map<Long, User> users;

    public Repository() {
        initUsers();
    }

    public Map<Long, User> getUsers() {
        return users;
    }

    private void initUsers() {
        users = new HashMap<>();

        final User kaliuha = new User(1L, "Vlad", "Kaliuha", "kaliuha@epam.com", "kaliuha1", true, "");
        final User admin = new User(2L, "Bohdan", "Admin", "admin@epam.com", "admin2", false, "");
        final User sevriukov = new User(3L, "Igor", "Sevriukov", "sevriukov@epam.com", "sevriukov3", true, "");

        users.put(kaliuha.getId(), kaliuha);
        users.put(admin.getId(), admin);
        users.put(sevriukov.getId(), sevriukov);
    }
}
