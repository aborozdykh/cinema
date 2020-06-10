package me.aborozdykh.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import me.aborozdykh.cinema.dto.MovieDto;
import me.aborozdykh.cinema.models.Movie;
import me.aborozdykh.cinema.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andrii Borozdykh
 */
@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController (MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/add")
    public Movie addMovie(@RequestParam String title, @RequestParam String description){
        var movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        movieService.add(movie);
        return movie;
    }

    @GetMapping
    public List<MovieDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(MovieDto::new)
                .collect(Collectors.toList());
    }
}
