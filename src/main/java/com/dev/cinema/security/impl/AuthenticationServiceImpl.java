package com.dev.cinema.security.impl;

import static com.dev.cinema.util.HashUtil.getSalt;
import static com.dev.cinema.util.HashUtil.hashPassword;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.models.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final String INCORRECT_LOGIN_OR_PASSWORD = "Enter valid login and password.";

    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        var user = userService.findByEmail(email).orElseThrow(() ->
                new AuthenticationException(INCORRECT_LOGIN_OR_PASSWORD));
        if (user.getPassword().equals(hashPassword(password, user.getSalt()))) {
            return user;
        }
        throw new AuthenticationException(INCORRECT_LOGIN_OR_PASSWORD);
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
