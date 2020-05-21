package com.dev.cinema.dao;

import java.time.LocalDate;
import java.util.List;
import com.dev.cinema.models.MovieSession;

public interface MovieSessionDao {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);
}
