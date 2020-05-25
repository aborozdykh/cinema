package me.aborozdykh.cinema.dao;

import java.util.Optional;
import me.aborozdykh.cinema.models.User;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);
}
