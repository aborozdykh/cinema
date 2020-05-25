package me.aborozdykh.cinema.dao;

import java.util.List;
import me.aborozdykh.cinema.models.Movie;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();
}
