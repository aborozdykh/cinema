package com.dev.cinema.dao.impl;

import java.time.LocalDate;
import java.util.List;
import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.models.MovieSession;

public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return null;
    }

    @Override
    public MovieSession add(MovieSession session) {
        return null;
    }
}
