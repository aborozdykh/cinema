package me.aborozdykh.cinema.service.impl;

import me.aborozdykh.cinema.dao.MovieDao;
import me.aborozdykh.cinema.lib.Inject;
import me.aborozdykh.cinema.lib.Service;
import me.aborozdykh.cinema.models.Movie;
import me.aborozdykh.cinema.service.MovieService;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
