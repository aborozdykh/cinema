package me.aborozdykh.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import me.aborozdykh.cinema.models.Movie;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andrii Borozdykh
 */
@RestController
@RequestMapping("/moviesession")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private CinemaHallService cinemaHallService;
    private MovieService movieService;

    @Autowired
    public MovieSessionController(MovieSessionService movieSessionService,
                                  CinemaHallService cinemaHallService,
                                  MovieService movieService) {
        this.movieSessionService = movieSessionService;
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }

//    @GetMapping
//    public List<MovieSessionResponseDto> getAllMovieSessions(
//            @RequestBody MovieSessionRequestDto movieSessionRequestDto){
//        return movieSessionService.findAvailableSessions(
//                movieSessionRequestDto.getMovieId(),
//                movieSessionRequestDto.getShowTime()).stream()
//                .map(MovieSessionResponseDto::new)
//                .collect(Collectors.toList());
//    }

    @PostMapping
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
