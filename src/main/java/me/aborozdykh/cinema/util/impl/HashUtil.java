package me.aborozdykh.cinema.util.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import me.aborozdykh.cinema.exceptions.AuthenticationException;
import me.aborozdykh.cinema.util.HashUtilService;
import org.springframework.stereotype.Service;

@Service
public class HashUtil implements HashUtilService {
    private static final String ALGORITHM = "SHA-512";

    public byte[] getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    public String hashPassword(String password, byte[] salt) throws AuthenticationException {
        StringBuilder hashedPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte b : digest) {
                hashedPassword.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new AuthenticationException("Can't hash password", e);
        }
        return hashedPassword.toString();
    }
}
