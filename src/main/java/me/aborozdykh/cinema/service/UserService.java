package me.aborozdykh.cinema.service;

import java.util.List;
import java.util.Optional;
import me.aborozdykh.cinema.models.User;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);

    List<User> getAll();
}
