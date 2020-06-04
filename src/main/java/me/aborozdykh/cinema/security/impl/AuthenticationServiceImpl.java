package me.aborozdykh.cinema.security.impl;

import me.aborozdykh.cinema.exceptions.AuthenticationException;
import me.aborozdykh.cinema.lib.Inject;
import me.aborozdykh.cinema.lib.Service;
import me.aborozdykh.cinema.models.User;
import me.aborozdykh.cinema.security.AuthenticationService;
import me.aborozdykh.cinema.service.ShoppingCartService;
import me.aborozdykh.cinema.service.UserService;
import me.aborozdykh.cinema.util.HashUtilService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final String INCORRECT_LOGIN_OR_PASSWORD = "Enter valid login and password.";

    @Inject
    private UserService userService;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Inject
    private HashUtilService hashUtilService;

    public AuthenticationServiceImpl() {
    }

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     HashUtilService hashUtilService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.hashUtilService = hashUtilService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        var user = userService.findByEmail(email).orElseThrow();
        if (user.getPassword().equals(hashUtilService.hashPassword(password, user.getSalt()))) {
            return user;
        }
        throw new AuthenticationException(INCORRECT_LOGIN_OR_PASSWORD);
    }

    @Override
    public User register(String email, String password) throws AuthenticationException {
        var user = new User();
        user.setEmail(email);
        byte[] salt = hashUtilService.getSalt();
        user.setSalt(salt);
        String hashPassword = hashUtilService.hashPassword(password, salt);
        user.setPassword(hashPassword);
        var userFromDb = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userFromDb);
        return userFromDb;
    }
}
