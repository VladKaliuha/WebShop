package com.epam.preproduction.kaliuha.service.customer.impl;

import com.epam.preproduction.kaliuha.dao.user.UserDao;
import com.epam.preproduction.kaliuha.dataBase.transaction.TransactionManager;
import com.epam.preproduction.kaliuha.entity.impl.User;
import com.epam.preproduction.kaliuha.service.customer.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private TransactionManager transactionManager;

    public UserServiceImpl(UserDao userDao, TransactionManager transactionManager) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public Optional<User> getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    public Optional<User> getUser(String email) {
        return userDao.getUser(email);
    }

    @Override
    public void add(User newUser) {
        transactionManager.doTransaction(() -> userDao.add(newUser));
    }
}
