package com.dev.cinema.dao;

import com.dev.cinema.models.User;

public interface UserDao {
    User add(User user);

    User findByEmail(String email);
}
