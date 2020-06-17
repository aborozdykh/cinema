package me.aborozdykh.cinema.security.impl;

import me.aborozdykh.cinema.models.Role;
import me.aborozdykh.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @author Andrii Borozdykh
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = userService.findByEmail(username);
        UserBuilder builder;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());
            var roleSet = user.getRoles();
            String[] roles = roleSet.stream()
                    .map(Role::getRoleName)
                    .map(Enum::name)
                    .toArray(String[]::new);
            builder.roles(roles);
            return builder.build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
