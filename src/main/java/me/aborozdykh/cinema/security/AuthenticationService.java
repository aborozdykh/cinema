package me.aborozdykh.cinema.security;

import me.aborozdykh.cinema.exceptions.AuthenticationException;
import me.aborozdykh.cinema.models.User;

public interface AuthenticationService {
    User register(String email, String password) throws AuthenticationException;
}
