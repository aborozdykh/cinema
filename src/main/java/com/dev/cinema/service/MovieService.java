package com.dev.cinema.service;

import java.util.List;
import com.dev.cinema.models.Movie;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
