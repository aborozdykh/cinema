package me.aborozdykh.cinema.security.impl;

import java.util.Set;
import me.aborozdykh.cinema.exceptions.AuthenticationException;
import me.aborozdykh.cinema.models.User;
import me.aborozdykh.cinema.security.AuthenticationService;
import me.aborozdykh.cinema.service.RoleService;
import me.aborozdykh.cinema.service.ShoppingCartService;
import me.aborozdykh.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public AuthenticationServiceImpl(
            UserService userService,
            ShoppingCartService shoppingCartService,
            PasswordEncoder passwordEncoder,
            RoleService roleService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        var user = userService.findByEmail(email);
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        throw new AuthenticationException("Enter valid login and password.");
    }

    @Override
    public User register(String email, String password) throws AuthenticationException {
        var user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        var role = roleService.getRoleByName("USER");
        user.setRoles(Set.of(role));
        var userFromDb = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userFromDb);
        return userFromDb;
    }
}
