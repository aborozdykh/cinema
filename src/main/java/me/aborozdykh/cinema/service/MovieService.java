package me.aborozdykh.cinema.service;

import me.aborozdykh.cinema.models.Movie;
import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
