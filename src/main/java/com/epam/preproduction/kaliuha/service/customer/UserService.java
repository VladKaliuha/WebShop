package com.epam.preproduction.kaliuha.service.customer;

import com.epam.preproduction.kaliuha.entity.impl.User;
import com.epam.preproduction.kaliuha.service.Service;

import java.util.Optional;

public interface UserService extends Service {

    /**
     * Return user by id
     *
     * @param id user id
     * @return suitable user
     */
    Optional<User> getUser(long id);

    /**
     * Return user by email
     *
     * @param email user email
     * @return suitable user
     */
    Optional<User> getUser(String email);

    /**
     * Add new user into storage
     *
     * @param newUser user for adding
     */
    void add(User newUser);
}
