package com.dev.cinema.security.impl;

import static com.dev.cinema.util.HashUtil.getSalt;
import static com.dev.cinema.util.HashUtil.hashPassword;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.models.User;
import com.dev.cinema.security.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserDao userDao;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        return null;
    }

    @Override
    public User register(String email, String password) {
        var user = new User();
        user.setEmail(email);
        byte[] salt = getSalt();
        user.setSalt(salt);
        String hashPassword = hashPassword(password, salt);
        user.setPassword(hashPassword);
        return user;
    }
}
