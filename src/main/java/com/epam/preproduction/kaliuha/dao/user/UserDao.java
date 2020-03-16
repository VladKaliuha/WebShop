package com.epam.preproduction.kaliuha.dao.user;

import com.epam.preproduction.kaliuha.dao.Dao;
import com.epam.preproduction.kaliuha.entity.impl.User;

import java.util.Optional;

public interface UserDao extends Dao {

    Optional<User> getUser(long id);

    Optional<User> getUser(String email);

    void add(User newUser);
}
