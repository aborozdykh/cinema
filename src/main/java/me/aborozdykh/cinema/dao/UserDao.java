package me.aborozdykh.cinema.dao;

import me.aborozdykh.cinema.models.User;
import java.util.Optional;

public interface UserDao {
    Optional<User> add(User user);

    Optional<User> findByEmail(String email);
}
