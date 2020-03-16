package com.epam.preproduction.kaliuha.dao.user.impl;

import com.epam.preproduction.kaliuha.dao.user.UserDao;
import com.epam.preproduction.kaliuha.entity.impl.User;

import java.util.Map;
import java.util.Optional;

public class UserDaoLocalStorage implements UserDao {

    private Map<Long, User> users;

    public UserDaoLocalStorage(Map<Long, User> users) {
        this.users = users;
    }

    @Override
    public Optional<User> getUser(long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> getUser(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public void add(User newUser) {
        users.put(newUser.getId(), newUser);
    }
}

