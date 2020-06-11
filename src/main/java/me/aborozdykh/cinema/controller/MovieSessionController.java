package me.aborozdykh.cinema.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import me.aborozdykh.cinema.models.dto.MovieSessionRequestDto;
import me.aborozdykh.cinema.models.dto.MovieSessionResponseDto;
import me.aborozdykh.cinema.models.mappers.MovieSessionMapper;
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
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    @Autowired
    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAllMovieSessions(
            @RequestParam Long movieId,
            @RequestParam LocalDate date) {
        return movieSessionService
                .findAvailableSessions(movieId, date)
                .stream()
                .map(movieSessionMapper::getMovieSessionResponseDtoFromMovieSession)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(movieSessionMapper.getMovieSessionFromDto(movieSessionRequestDto));
    }
}
