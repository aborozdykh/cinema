package me.aborozdykh.cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import me.aborozdykh.cinema.exceptions.AuthenticationException;
import me.aborozdykh.cinema.lib.Injector;
import me.aborozdykh.cinema.models.CinemaHall;
import me.aborozdykh.cinema.models.Movie;
import me.aborozdykh.cinema.models.MovieSession;
import me.aborozdykh.cinema.security.AuthenticationService;
import me.aborozdykh.cinema.service.CinemaHallService;
import me.aborozdykh.cinema.service.MovieService;
import me.aborozdykh.cinema.service.MovieSessionService;
import me.aborozdykh.cinema.service.UserService;

public class Main {
    private static Injector injector = Injector.getInstance("me.aborozdykh.cinema");

    public static void main(String[] args) throws AuthenticationException {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.getAll().forEach(System.out::println);

        // Add new Movie
        Movie movie = new Movie();
        movie.setTitle("Phantomas returns");
        movie.setDescription("The best movie about Phantomas");
        movieService.add(movie);

        CinemaHallService cinemaHallService
                = (CinemaHallService) injector.getInstance(CinemaHallService.class);

        //Add a new CinemaHall - this is required because users will watch the movie in the cinema
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100); // Only 100 users can sit in this hall
        cinemaHall = cinemaHallService.add(cinemaHall);

        //Add a new MovieSession. Users selects the movie and than selects the session.
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30));
        movieSession.setShowTime(dateTime);

        MovieSessionService movieSessionService
                = (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);

        movieSessionService.findAvailableSessions(movie.getId(), LocalDate.now())
                .forEach(System.out::println);

        //Add a new User
        AuthenticationService authenticationService
                = (AuthenticationService) injector.getInstance(AuthenticationService.class);
        var user = authenticationService.register("aborozdykh@gmail.com", "CoolPassword1_!");
        var user2 = authenticationService.register("aborozdykh@gmail.com", "CoolPassword1_!");
        UserService userService = (UserService) injector.getInstance(UserService.class);
        userService.add(user);
        System.out.println("User findByEmail: " + userService.findByEmail("aborozdykh@gmail.com"));
        System.out.println("User findByEmail with wrong email: "
                + userService.findByEmail("test@gmail.com"));

        //Try to login
        var userWithCorrectLoginAndPassword
                = authenticationService.login("aborozdykh@gmail.com", "CoolPassword1_!");
        System.out.println("User with correct login and password: "
                + userWithCorrectLoginAndPassword);
    }
}
