package me.aborozdykh.cinema.controller;

import java.time.LocalDateTime;
import me.aborozdykh.cinema.exceptions.AuthenticationException;
import me.aborozdykh.cinema.models.CinemaHall;
import me.aborozdykh.cinema.models.Movie;
import me.aborozdykh.cinema.models.MovieSession;
import me.aborozdykh.cinema.security.AuthenticationService;
import me.aborozdykh.cinema.service.CinemaHallService;
import me.aborozdykh.cinema.service.MovieService;
import me.aborozdykh.cinema.service.MovieSessionService;
import me.aborozdykh.cinema.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andrii Borozdykh
 */
@RestController
public class InjectController {
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;
    private final MovieSessionService movieSessionService;
    private final AuthenticationService authenticationService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public InjectController(CinemaHallService cinemaHallService,
                            MovieService movieService,
                            MovieSessionService movieSessionService,
                            AuthenticationService authenticationService,
                            ShoppingCartService shoppingCartService) {
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
        this.movieSessionService = movieSessionService;
        this.authenticationService = authenticationService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/inject")
    public String inject() throws AuthenticationException {
        var cinemaHallBlueIce
                = cinemaHallService.add(new CinemaHall(100, "Blue Ice"));
        var cinemaHallYellowWarm
                = cinemaHallService.add(new CinemaHall(200, "Yellow Warm"));
        var cinemaHallRedHot
                = cinemaHallService.add(new CinemaHall(300, "Red Hot"));

        var moviePhantomas = movieService.add(new Movie("Phantomas returns",
                "The best movie about Phantomas"));
        var movieBatman = movieService.add(new Movie("Batman", "Crazy flying mouse"));
        var movieHarryPotter = movieService.add(new Movie("Harry Potter",
                "Magic and nothing else"));

        var movieSessionPhantomas = movieSessionService.add(new MovieSession(moviePhantomas,
                cinemaHallBlueIce, LocalDateTime.now()));
        var movieSessionBatman = movieSessionService.add(new MovieSession(movieBatman,
                cinemaHallYellowWarm, LocalDateTime.now()));
        var movieSessionHarryPotter = movieSessionService.add(new MovieSession(movieHarryPotter,
                cinemaHallRedHot, LocalDateTime.now()));

        var nifNif = authenticationService.register("nif-nif@pigs.com", "nif-nif");
        var nafNaf = authenticationService.register("naf-naf@pigs.com", "naf-naf");
        var nufNuf = authenticationService.register("nuf-nuf@pigs.com", "nuf-nuf");

        shoppingCartService.addSession(movieSessionPhantomas, nifNif);
        shoppingCartService.addSession(movieSessionBatman, nafNaf);
        shoppingCartService.addSession(movieSessionHarryPotter, nufNuf);

        return "Cinema halls, Movies, MovieSessions, Users, Shopping carts was injected";
    }
}
