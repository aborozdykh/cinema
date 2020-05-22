package com.dev.cinema.service.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.models.User;
import com.dev.cinema.service.UserService;

public class UserServiceImpl implements UserService {
    @Inject
    UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
