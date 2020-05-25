package me.aborozdykh.cinema.dao;

import me.aborozdykh.cinema.models.Movie;
import java.util.List;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();
}
