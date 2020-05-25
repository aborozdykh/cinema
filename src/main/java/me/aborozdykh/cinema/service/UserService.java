package me.aborozdykh.cinema.service;

import me.aborozdykh.cinema.models.User;
import java.util.Optional;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);
}
