package me.aborozdykh.cinema.controller;

import me.aborozdykh.cinema.models.dto.ShoppingCartResponseDto;
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
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final MovieSessionService movieSessionService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionService movieSessionService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
    }

    @GetMapping("/byuser")
    public ShoppingCartResponseDto getShoppingCartByUserId(@RequestParam Long userId) {
        var user = userService.get(userId);
        var shoppingCart = shoppingCartService.getByUser(user);
        return new ShoppingCartResponseDto(shoppingCart);
    }

    @PostMapping("/addmoviesession")
    public void addMovieSessionToShoppingCart(@RequestParam Long movieSessionId,
                                              @RequestParam Long userId) {
        var user = userService.get(userId);
        var movieSession = movieSessionService.get(movieSessionId);
        shoppingCartService.addSession(movieSession, user);
    }
}
