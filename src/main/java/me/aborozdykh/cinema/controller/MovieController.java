package me.aborozdykh.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import me.aborozdykh.cinema.models.dto.MovieRequestDto;
import me.aborozdykh.cinema.models.Movie;
import me.aborozdykh.cinema.models.dto.MovieResponseDto;
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

    @Autowired
    public MovieController (MovieService movieService){
        this.movieService = movieService;
    }

    @PostMapping("/add")
    public void addMovie(@RequestBody MovieRequestDto movieRequestDto){
        var movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        movieService.add(movie);
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(MovieResponseDto::new)
                .collect(Collectors.toList());
    }
}
