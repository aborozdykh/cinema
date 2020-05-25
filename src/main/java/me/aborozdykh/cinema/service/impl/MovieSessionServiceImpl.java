package me.aborozdykh.cinema.service.impl;

import me.aborozdykh.cinema.lib.Inject;
import me.aborozdykh.cinema.lib.Service;
import me.aborozdykh.cinema.dao.MovieSessionDao;
import me.aborozdykh.cinema.models.MovieSession;
import me.aborozdykh.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }
}
