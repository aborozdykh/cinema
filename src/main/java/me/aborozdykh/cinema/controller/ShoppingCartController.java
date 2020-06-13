package me.aborozdykh.cinema.controller;

import me.aborozdykh.cinema.models.dto.ShoppingCartResponseDto;
import me.aborozdykh.cinema.models.mappers.ShoppingCartMapper;
import me.aborozdykh.cinema.security.util.SecurityUtils;
import me.aborozdykh.cinema.service.MovieSessionService;
import me.aborozdykh.cinema.service.ShoppingCartService;
import me.aborozdykh.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andrii Borozdykh
 */
@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final MovieSessionService movieSessionService;
    private final ShoppingCartMapper shoppingCartMapper;
    private final SecurityUtils securityUtils;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionService movieSessionService,
                                  ShoppingCartMapper shoppingCartMapper,
                                  SecurityUtils securityUtils) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
        this.shoppingCartMapper = shoppingCartMapper;
        this.securityUtils = securityUtils;
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getShoppingCartByUser() {
        var user = userService.findByEmail(securityUtils.getEmail());
        var shoppingCart = shoppingCartService.getByUser(user);
        return shoppingCartMapper.getShoppingCartResponseDtoFromShoppingCart(shoppingCart);
    }

    @PostMapping("/add-movie-session")
    public void addMovieSessionToShoppingCart(@RequestParam Long movieSessionId) {
        var user = userService.findByEmail(securityUtils.getEmail());
        var movieSession = movieSessionService.get(movieSessionId);
        shoppingCartService.addSession(movieSession, user);
    }
}
