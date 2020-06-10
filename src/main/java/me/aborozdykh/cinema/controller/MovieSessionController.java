package me.aborozdykh.cinema.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import me.aborozdykh.cinema.models.MovieSession;
import me.aborozdykh.cinema.models.dto.MovieSessionRequestDto;
import me.aborozdykh.cinema.models.dto.MovieSessionResponseDto;
import me.aborozdykh.cinema.service.CinemaHallService;
import me.aborozdykh.cinema.service.MovieService;
import me.aborozdykh.cinema.service.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andrii Borozdykh
 */
@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;

    @Autowired
    public MovieSessionController(MovieSessionService movieSessionService,
                                  CinemaHallService cinemaHallService,
                                  MovieService movieService) {
        this.movieSessionService = movieSessionService;
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAllMovieSessions(
            @RequestParam Long movieId,
            @RequestParam LocalDate date){
        return movieSessionService
                .findAvailableSessions(movieId, date)
                .stream()
                .map(MovieSessionResponseDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public void addMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(getMovieSessionFromDto(movieSessionRequestDto));
    }

    private MovieSession getMovieSessionFromDto(MovieSessionRequestDto movieSessionRequestDto) {
        var movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHallService.get(movieSessionRequestDto.getCinemaHallId()));
        movieSession.setMovie(movieService.get(movieSessionRequestDto.getMovieId()));
        movieSession.setShowTime(movieSessionRequestDto.getShowTime());
        return movieSession;
    }
}
