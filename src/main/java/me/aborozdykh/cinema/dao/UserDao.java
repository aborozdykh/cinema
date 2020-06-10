package me.aborozdykh.cinema.dao;

import java.util.List;
import java.util.Optional;
import me.aborozdykh.cinema.models.User;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByEmail(String email);

    List<User> getAll();
}
