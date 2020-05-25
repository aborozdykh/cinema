package com.dev.cinema.dao;

import com.dev.cinema.models.User;
import java.util.Optional;

public interface UserDao {
    Optional<User> add(User user);

    Optional<User> findByEmail(String email);
}
