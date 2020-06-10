package me.aborozdykh.cinema.service.impl;

import java.util.List;
import java.util.Optional;
import me.aborozdykh.cinema.dao.UserDao;
import me.aborozdykh.cinema.models.User;
import me.aborozdykh.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
