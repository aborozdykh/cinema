package me.aborozdykh.cinema.service.impl;

import java.util.List;
import me.aborozdykh.cinema.dao.MovieDao;
import me.aborozdykh.cinema.models.Movie;
import me.aborozdykh.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id);
    }

    @Override
    public void update(Movie movie) {
        movieDao.update(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
