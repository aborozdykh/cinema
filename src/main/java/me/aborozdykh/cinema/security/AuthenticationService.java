package me.aborozdykh.cinema.security;

import me.aborozdykh.cinema.exceptions.AuthenticationException;
import me.aborozdykh.cinema.models.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    /**
     * We should register a new user. New user entity will contains the email and password
     *
     * @param email    - user email. should be unique for each user
     * @param password - user password
     * @return new user instance
     */
    User register(String email, String password);
}
