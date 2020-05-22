package com.dev.cinema.security.impl;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.models.User;
import com.dev.cinema.security.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public User login(String email, String password) throws AuthenticationException {
        return null;
    }

    @Override
    public User register(String email, String password) {
        return null;
    }
}
