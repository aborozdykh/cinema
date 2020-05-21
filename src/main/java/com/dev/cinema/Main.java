package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.models.CinemaHall;
import com.dev.cinema.models.Movie;
import com.dev.cinema.models.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
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
    }
}
