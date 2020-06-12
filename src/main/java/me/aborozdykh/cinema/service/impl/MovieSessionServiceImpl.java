package me.aborozdykh.cinema.service.impl;

import java.time.LocalDate;
import java.util.List;
import me.aborozdykh.cinema.dao.MovieSessionDao;
import me.aborozdykh.cinema.models.MovieSession;
import me.aborozdykh.cinema.service.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private final MovieSessionDao movieSessionDao;

    @Autowired
    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }

    @Override
    public MovieSession get(Long id) {
        return movieSessionDao.get(id);
    }

    @Override
    public void update(MovieSession movieSession) {
        movieSessionDao.update(movieSession);
    }
}
