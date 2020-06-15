package me.aborozdykh.cinema.security.impl;

import me.aborozdykh.cinema.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;

/**
 * @author Andrii Borozdykh
 */
@Service
public class CustomUserDetailsImpl implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByEmail(username);
        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());
            builder.roles(user.getRoles().toString());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        return builder.build();
    }
}
