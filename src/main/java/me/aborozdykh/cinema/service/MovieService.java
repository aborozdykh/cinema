package me.aborozdykh.cinema.service;

import java.util.List;
import me.aborozdykh.cinema.models.Movie;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
