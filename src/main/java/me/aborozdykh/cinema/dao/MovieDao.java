package me.aborozdykh.cinema.dao;

import java.util.List;
import me.aborozdykh.cinema.models.Movie;

public interface MovieDao extends AbstractDao<Movie> {
    List<Movie> getAll();
}
