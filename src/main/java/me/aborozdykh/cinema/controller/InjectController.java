package me.aborozdykh.cinema.controller;

import java.time.LocalDateTime;
import java.util.Set;
import javax.annotation.PostConstruct;
import me.aborozdykh.cinema.exceptions.AuthenticationException;
import me.aborozdykh.cinema.models.CinemaHall;
import me.aborozdykh.cinema.models.Movie;
import me.aborozdykh.cinema.models.MovieSession;
import me.aborozdykh.cinema.models.Role;
import me.aborozdykh.cinema.models.User;
import me.aborozdykh.cinema.security.AuthenticationService;
import me.aborozdykh.cinema.service.CinemaHallService;
import me.aborozdykh.cinema.service.MovieService;
import me.aborozdykh.cinema.service.MovieSessionService;
import me.aborozdykh.cinema.service.RoleService;
import me.aborozdykh.cinema.service.ShoppingCartService;
import me.aborozdykh.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InjectController(RoleService roleService,
                            PasswordEncoder passwordEncoder,
                            CinemaHallService cinemaHallService,
                            MovieService movieService,
                            MovieSessionService movieSessionService,
                            AuthenticationService authenticationService,
                            ShoppingCartService shoppingCartService,
                            UserService userService) {
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
        this.movieSessionService = movieSessionService;
        this.authenticationService = authenticationService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @PostConstruct
    public void insertRolesToDb() {
        roleService.add(Role.of("ADMIN"));
        roleService.add(Role.of("USER"));
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

        var admin = new User();
        admin.setEmail("admin@pigs.com");
        admin.setPassword(passwordEncoder.encode("admin"));
        var adminRole = roleService.getRoleByName("ADMIN");
        var userRole = roleService.getRoleByName("USER");
        admin.setRoles(Set.of(adminRole, userRole));
        userService.add(admin);

        return "Cinema halls, Movies, MovieSessions, Users, Shopping carts was injected";
    }
}
