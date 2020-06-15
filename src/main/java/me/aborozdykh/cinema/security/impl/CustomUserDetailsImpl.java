package me.aborozdykh.cinema.security.impl;

import me.aborozdykh.cinema.dao.UserDao;
import me.aborozdykh.cinema.models.dto.UserDetailsDto;
import me.aborozdykh.cinema.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Andrii Borozdykh
 */
@Service
public class CustomUserDetailsImpl implements UserDetailsService {
    private final UserDao userDao;

    public CustomUserDetailsImpl(UserService userService, UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userDao.findByEmail(username);
        return new UserDetailsDto(user);
    }
}
