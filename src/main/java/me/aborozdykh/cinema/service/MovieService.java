package me.aborozdykh.cinema.service;

import java.util.List;
import me.aborozdykh.cinema.models.Movie;

public interface MovieService extends GenericService<Movie>{
    List<Movie> getAll();
}
