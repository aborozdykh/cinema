package me.aborozdykh.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import me.aborozdykh.cinema.models.dto.MovieRequestDto;
import me.aborozdykh.cinema.models.dto.MovieResponseDto;
import me.aborozdykh.cinema.models.mappers.MovieMapper;
import me.aborozdykh.cinema.service.MovieService;
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
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping("/add")
    public void addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.add(movieMapper.getMovieFromMovieRequestDto(movieRequestDto));
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll()
                .stream()
                .map(movieMapper::getMovieResponseDtoFromMovie)
                .collect(Collectors.toList());
    }
}
