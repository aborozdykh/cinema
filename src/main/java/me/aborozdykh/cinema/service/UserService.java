package me.aborozdykh.cinema.service;

import java.util.List;
import me.aborozdykh.cinema.models.User;

public interface UserService {
    User add(User user);

    User get(Long id);

    User findByEmail(String email);

    List<User> getAll();
}
