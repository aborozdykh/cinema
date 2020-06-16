package me.aborozdykh.cinema.service;

import java.util.List;
import me.aborozdykh.cinema.models.User;

public interface UserService extends AbstractService<User> {
    User findByEmail(String email);

    List<User> getAll();
}
