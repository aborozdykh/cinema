package me.aborozdykh.cinema.util;

import me.aborozdykh.cinema.exceptions.AuthenticationException;

public interface HashUtilService {
    byte[] getSalt();

    String hashPassword(String password, byte[] salt) throws AuthenticationException;
}
