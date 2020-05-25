package me.aborozdykh.cinema.service;

import me.aborozdykh.cinema.models.User;
import java.util.Optional;

public interface UserService {
    Optional<User> add(User user);

    Optional<User> findByEmail(String email);
}
